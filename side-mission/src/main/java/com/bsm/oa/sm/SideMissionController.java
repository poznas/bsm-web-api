package com.bsm.oa.sm;


import static com.bsm.oa.sm.SideMissionController.SM_CONTEXT;

import com.bsm.oa.sm.model.PerformParamSymbol;
import com.bsm.oa.sm.model.RaterType;
import com.bsm.oa.sm.model.SideMissionReport;
import com.bsm.oa.sm.model.SideMissionReportId;
import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.model.SideMissionTypeID;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import com.bsm.oa.sm.service.SideMissionService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping(SM_CONTEXT)
@RequiredArgsConstructor
public class SideMissionController {

  static final String SM_CONTEXT = "/side-mission";
  private static final String SM_MISSION_TYPE = "/type";
  private static final String SM_MISSION_TYPE_ID = SM_MISSION_TYPE + "/{typeId}";
  private static final String SM_MISSION_TYPES = SM_MISSION_TYPE + "/types";
  private static final String SM_REPORT = "/report";
  private static final String SM_REPORT_ID = SM_REPORT + "/{reportId}";
  private static final String SM_REPORT_RATE = SM_REPORT_ID + "/rate/{raterType}";
  private static final String SM_REPORTS = SM_REPORT + "/reports/{raterType}";

  private final SideMissionService sideMissionService;

  @PutMapping(SM_MISSION_TYPE)
  @PreAuthorize("hasAuthority('PRV_EDIT_SM')")
  public void mergeSideMissionType(@Valid @NotNull @RequestBody SideMissionType missionType) {
    sideMissionService.mergeSideMissionType(missionType);
  }

  @GetMapping(SM_MISSION_TYPES)
  public List<SideMissionType> getSideMissionTypes() {
    return sideMissionService.getSideMissionTypes();
  }

  @GetMapping(SM_MISSION_TYPE_ID)
  public SideMissionType getSideMissionTypes(
    @Valid @NotNull @PathVariable("typeId") SideMissionTypeID typeId) {
    return sideMissionService.getSideMissionType(typeId);
  }

  @PostMapping(SM_REPORT)
  @PreAuthorize("hasAuthority('PRV_REPORT_SM')")
  public void reportSideMission(@Valid @NotNull @RequestBody ReportSideMissionRequest request) {
    sideMissionService.reportSideMission(request);
  }

  @GetMapping(SM_REPORTS)
  @PreAuthorize("hasAuthority('PRV_' + #raterType + '_RATE_SM')")
  public Page<SideMissionReport> getReportsForJudge(@PageableDefault Pageable pageable,
                                                    @NotNull @PathVariable("raterType") RaterType raterType
  ) {
    return sideMissionService.getSideMissionReports(raterType, pageable);
  }

  @PostMapping(SM_REPORT_RATE)
  @PreAuthorize("hasAuthority('PRV_' + #raterType + '_RATE_SM')")
  public void postReportRate(@NotNull @PathVariable("reportId") Long reportId,
                             @NotNull @PathVariable("raterType") RaterType raterType,
                             @Valid @NotEmpty @RequestBody Map<PerformParamSymbol, Double> rates) {
    sideMissionService.rateReport(SideMissionReportId.of(reportId), raterType, rates);
  }
}
