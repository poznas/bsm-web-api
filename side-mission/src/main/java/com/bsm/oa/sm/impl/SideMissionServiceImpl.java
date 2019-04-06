package com.bsm.oa.sm.impl;

import static com.bsm.oa.common.util.CollectionUtils.mapList;
import static com.bsm.oa.common.util.CollectionUtils.streamOfNullable;
import static com.bsm.oa.common.util.PageUtils.retrievePage;
import static com.bsm.oa.sm.exception.SideMissionException.REQUIRED_PROOF_TYPES_NOT_MATCHED;
import static com.bsm.oa.sm.exception.SideMissionException.SIDE_MISSION_TYPE_NOT_EXISTS;
import static com.bsm.oa.sm.model.ProofRequirementType.PHOTO_OR_VIDEO;
import static java.util.Optional.of;

import com.bsm.oa.common.service.UserDetailsProvider;
import com.bsm.oa.sm.dao.SideMissionRepository;
import com.bsm.oa.sm.model.ProofMediaLink;
import com.bsm.oa.sm.model.SideMissionReport;
import com.bsm.oa.sm.model.SideMissionReportFilter;
import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.model.ToRateBy;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import com.bsm.oa.sm.service.SideMissionService;
import com.bsm.oa.user.service.UserService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SideMissionServiceImpl implements SideMissionService {

  private final SideMissionRepository sideMissionRepository;
  private final UserDetailsProvider userDetailsProvider;
  private final UserService userService;

  @Override
  @Transactional
  public void mergeSideMissionType(@Valid @NotNull SideMissionType missionType) {
    sideMissionRepository.mergeSideMissionType(missionType);
  }

  @Override
  public List<SideMissionType> getSideMissionTypes() {
    return sideMissionRepository.getSideMissionTypes();
  }

  @Override
  @Transactional
  public void reportSideMission(@Valid @NotNull ReportSideMissionRequest request) {
    userService.assertUserExists(request.getPerformingUserId(), "performing user");

    var missionType = of(request.getMissionTypeId())
      .map(sideMissionRepository::getSideMissionType)
      .orElseThrow(SIDE_MISSION_TYPE_NOT_EXISTS);

    validateProofMedia(missionType, request.getProofMediaLinks());

    request.setReportingUserId(userDetailsProvider.getUserId());
    sideMissionRepository.insertSideMissionReport(request);
  }

  @Override
  public Page<SideMissionReport> getSideMissionReports(@NotNull ToRateBy toRateBy,
                                                       @NotNull Pageable pageable) {
    var filter = SideMissionReportFilter.of(toRateBy, userDetailsProvider.getUserId());

    return retrievePage(filter, pageable, sideMissionRepository::selectReports,
      sideMissionRepository::selectReportsCount);
  }

  private void validateProofMedia(@Valid @NotNull SideMissionType missionType,
                                  @Valid List<ProofMediaLink> proofMediaLinks) {

    var requiredProofTypes = stringProofTypes(missionType);

    long leftMediaTypes = streamOfNullable(proofMediaLinks)
      .map(ProofMediaLink::getType).map(Enum::name)
      .filter(provided -> !requiredProofTypes.remove(provided))
      .count();

    long requiredAnyMediaCount = requiredProofTypes.stream()
      .filter(type -> PHOTO_OR_VIDEO.name().equals(type)).count();

    long notMatchedRequiredSpecificMediaTypes = requiredProofTypes.size() - requiredAnyMediaCount;

    if (leftMediaTypes < requiredAnyMediaCount || notMatchedRequiredSpecificMediaTypes > 0) {

      String details = "Required: " + stringProofTypes(missionType)
        + ". Provided: " + stringProofTypes(proofMediaLinks);

      REQUIRED_PROOF_TYPES_NOT_MATCHED.raise(details);
    }
  }

  private List<String> stringProofTypes(@Valid @NotNull SideMissionType missionType) {
    return mapList(missionType.getProofRequirements(), x -> x.getType().name());
  }

  private List<String> stringProofTypes(@Valid List<ProofMediaLink> proofMediaLinks) {
    return mapList(proofMediaLinks, x -> x.getType().name());
  }

}
