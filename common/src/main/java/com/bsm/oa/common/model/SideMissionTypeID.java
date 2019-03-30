package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class SideMissionTypeID implements ValueObject<String> {

  @NotBlank
  @Size(max = 32)
  private String value;
}
