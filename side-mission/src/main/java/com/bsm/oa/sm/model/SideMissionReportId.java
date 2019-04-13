package com.bsm.oa.sm.model;

import com.bsm.oa.common.model.ValueObject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class SideMissionReportId implements ValueObject<Long> {

  @Min(1)
  @NotNull
  private Long value;

  public static SideMissionReportId of(@Min(1) @NotNull Long reportId) {
    return new SideMissionReportId(reportId);
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
