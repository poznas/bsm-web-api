package com.bsm.oa.sm.impl;

import com.bsm.oa.common.service.UserDetailsProvider;
import com.bsm.oa.sm.dao.SideMissionRepository;
import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import com.bsm.oa.sm.service.SideMissionService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SideMissionServiceImpl implements SideMissionService {

  private final SideMissionRepository sideMissionRepository;
  private final UserDetailsProvider userDetailsProvider;

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
    request.setPerformingUserId(userDetailsProvider.getUserId());
    sideMissionRepository.insertSideMissionReport(request);
  }
}
