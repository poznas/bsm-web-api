package com.agh.oa.points.model;

import com.bsm.oa.common.model.TeamId;
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
public class PointsDetails {

  /**
   * points complex identifier
   */
  @Valid
  @NotNull
  private PointsId id;

  /**
   * points amount
   */
  @NotNull
  private Double amount;

  /**
   * short label
   */
  private String shortLabel;

  /**
   * points achiever details
   */
  @Valid
  private User user;

  /**
   * team identifier
   */
  @Valid
  @NotNull
  private TeamId teamId;

  /**
   * points insert time
   */
  @NotNull
  private Timestamp timestamp;

}
