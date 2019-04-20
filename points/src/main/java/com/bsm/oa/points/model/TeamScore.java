package com.bsm.oa.points.model;

import com.bsm.oa.common.model.Team;
import java.io.Serializable;
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
public class TeamScore implements Serializable {

  /**
   * team details
   */
  @Valid
  @NotNull
  private Team team;

  /**
   * team total score
   */
  private int score;

}
