package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Value;

@Value
public class DictionaryKey implements ValueObject<String> {

  @NotBlank
  @Pattern(regexp = "[A-Z_]{1,128}")
  private String value;
}
