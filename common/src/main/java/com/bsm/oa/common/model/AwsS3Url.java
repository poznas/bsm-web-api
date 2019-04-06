package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Value;

@Value
public class AwsS3Url implements ValueObject<String> {

  @NotBlank
  @Pattern(regexp = "https://s3\\.[a-z0-9][a-z0-9-.]*\\.amazonaws\\.com/[a-z0-9][a-z0-9-./]*")
  private String value;
}
