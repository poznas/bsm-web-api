package com.bsm.oa.common.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Value;

@Value
public class HexColor implements ValueObject<String>, Serializable {

  @NotBlank
  @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
  private String value;

}
