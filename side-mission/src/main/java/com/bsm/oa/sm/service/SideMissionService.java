package com.bsm.oa.sm.service;

import com.bsm.oa.sm.model.SideMissionReport;
import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.model.ToRateBy;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SideMissionService {

  /**
   * Create or replace side mission type
   *
   * @param missionType mission type data
   */
  void mergeSideMissionType(@Valid @NotNull SideMissionType missionType);

  /**
   * @return side mission type list
   */
  List<SideMissionType> getSideMissionTypes();

  /**
   * Create side mission report
   *
   * @param request with report details
   */
  void reportSideMission(@Valid @NotNull ReportSideMissionRequest request);

  /**
   * @param toRateBy specifies kind of rater
   * @param pageable page request params
   * @return paged list of SM reports awaiting current user's rate
   */
  Page<SideMissionReport> getSideMissionReports(@NotNull ToRateBy toRateBy,
                                                @NotNull Pageable pageable);
}
