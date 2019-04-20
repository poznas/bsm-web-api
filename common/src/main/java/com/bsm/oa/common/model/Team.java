package com.bsm.oa.common.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team implements Serializable {

  /**
   * Team identifier
   */
  private TeamId teamId;

  /**
   * team display name
   */
  private String displayName;

  /**
   * team color
   */
  private HexColor color;

}
