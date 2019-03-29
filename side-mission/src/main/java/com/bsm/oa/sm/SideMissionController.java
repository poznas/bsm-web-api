package com.bsm.oa.sm;


import static com.bsm.oa.sm.SideMissionController.SM_CONTEXT;

import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.model.SideMissionTypeID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("squid:S1075")

@Validated
@RestController
@RequestMapping(SM_CONTEXT)
@RequiredArgsConstructor
public class SideMissionController {

  static final String SM_CONTEXT = "/side-mission";

  private static final String SM_MISSION_TYPE = "/type/{smTypeId}";

  @PutMapping(SM_MISSION_TYPE)
  @PreAuthorize("hasAuthority('PRV_EDIT_SM')")
  public void mergeSideMissionType(
    @Valid @NotNull @PathVariable("smTypeId") SideMissionTypeID typeId,
    @Valid @NotNull @RequestBody SideMissionType missionType) {

  }
}
