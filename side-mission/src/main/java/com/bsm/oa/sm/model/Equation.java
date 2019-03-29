package com.bsm.oa.sm.model;

import com.bsm.oa.common.model.ValueObject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class Equation implements ValueObject<String> {

  @NotBlank
  @Size(max = 500)
  private String value;
}
