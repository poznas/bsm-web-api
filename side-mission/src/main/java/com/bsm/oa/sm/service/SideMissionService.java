package com.bsm.oa.sm.service;

import com.bsm.oa.sm.model.SideMissionType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface SideMissionService {

  /**
   * Create or replace side mission type
   *
   * @param missionType mission type data
   */
  void mergeSideMissionType(@Valid @NotNull SideMissionType missionType);
}
