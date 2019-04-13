package com.bsm.oa.sm.model;

import java.util.List;
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
public class ReportRateData {

  /**
   * side mission report identifier
   */
  @Valid
  @NotNull
  private SideMissionReportId reportId;

  /**
   * Equation for the number of points
   */
  @Valid
  @NotNull
  private Equation equation;

  /**
   * Side mission perform param rates
   */
  @NotNull
  private List<PerformParamRate> paramRates;
}
