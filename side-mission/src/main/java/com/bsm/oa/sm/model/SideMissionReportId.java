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
}
