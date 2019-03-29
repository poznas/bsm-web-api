package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class DictionaryName implements ValueObject<String> {

  @NotBlank
  @Size(max = 128)
  private String value;
}
