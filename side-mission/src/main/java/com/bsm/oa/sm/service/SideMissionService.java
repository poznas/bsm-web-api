package com.bsm.oa.sm.service;

import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

}
