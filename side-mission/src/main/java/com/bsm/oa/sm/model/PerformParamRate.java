package com.bsm.oa.sm.model;

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
public class PerformParamRate {

  /**
   * Symbol representing parameter in mission points equation
   */
  @Valid
  @NotNull
  private PerformParamSymbol symbol;

  /**
   * Rate value
   */
  @NotNull
  private Double assignedValue;

}
