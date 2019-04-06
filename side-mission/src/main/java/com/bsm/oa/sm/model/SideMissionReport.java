package com.bsm.oa.sm.model;

import com.bsm.oa.common.model.User;
import java.sql.Timestamp;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SideMissionReport {

  /**
   * side mission report identifier
   */
  @Valid
  @NotNull
  private SideMissionReportId reportId;

  /**
   * side mission type identifier
   */
  @Valid
  @NotNull
  private SideMissionTypeID missionTypeId;

  /**
   * performing user details
   */
  @Valid
  @NotNull
  private User performingUser;

  /**
   * reporting user details
   */
  @Valid
  @NotNull
  private User reportingUser;

  /**
   * report timestamp
   */
  @NotNull
  private Timestamp reportTimestamp;

}
