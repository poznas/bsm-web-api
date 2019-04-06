package com.bsm.oa.sm;


import static com.bsm.oa.sm.SideMissionController.SM_CONTEXT;
import static com.bsm.oa.sm.model.ToRateBy.JUDGE;
import static com.bsm.oa.sm.model.ToRateBy.PROFESSOR;

import com.bsm.oa.sm.model.SideMissionReport;
import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import com.bsm.oa.sm.service.SideMissionService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
  private static final String SM_MISSION_TYPES = SM_MISSION_TYPE + "/types";
  private static final String SM_REPORT = "/report";
  private static final String SM_REPORTS = SM_REPORT + "/reports";
  private static final String SM_REPORTS_FOR_JUDGE = SM_REPORTS + "/for-judge";
  private static final String SM_REPORTS_FOR_PROFESSOR = SM_REPORTS + "/for-professor";

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

  @PostMapping(SM_REPORT)
  @PreAuthorize("hasAuthority('PRV_REPORT_SM')")
  public void reportSideMission(@Valid @NotNull @RequestBody ReportSideMissionRequest request) {
    sideMissionService.reportSideMission(request);
  }

  @GetMapping(SM_REPORTS_FOR_JUDGE)
  @PreAuthorize("hasAuthority('PRV_JUDGE_SM')")
  public Page<SideMissionReport> getReportsForJudge(@PageableDefault Pageable pageable) {
    return sideMissionService.getSideMissionReports(JUDGE, pageable);
  }

  @GetMapping(SM_REPORTS_FOR_PROFESSOR)
  @PreAuthorize("hasAuthority('PRV_PROFESSOR_RATE_SM')")
  public Page<SideMissionReport> getReportsForProfessor(@PageableDefault Pageable pageable) {
    return sideMissionService.getSideMissionReports(PROFESSOR, pageable);
  }
}
