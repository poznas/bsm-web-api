package com.bsm.oa.common.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Value;

@Value
public class IsoLangCode implements ValueObject<String> {

  @NotNull
  @Pattern(regexp = "[A-Z]{2}")
  private String value;
}
