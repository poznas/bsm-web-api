package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Value;

@Value
public class AwsS3Url implements ValueObject<String> {

  @NotBlank
  @Pattern(regexp = "https://[a-z0-9][a-z0-9-.]*\\.s3\\.amazonaws\\.com/[w][wW]*")
  private String value;
}
