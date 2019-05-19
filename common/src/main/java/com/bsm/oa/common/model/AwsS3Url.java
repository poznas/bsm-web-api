package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Value;

@Value
public class AwsS3Url implements ValueObject<String> {

  private static final String PATTERN
    = "https://[a-z0-9][a-z0-9-.]*s3\\.[a-z0-9][a-z0-9-.]*\\.amazonaws\\.com/[a-z0-9][a-zA-Z0-9-.%/]*";

  @NotBlank
  @Pattern(regexp = PATTERN)
  private String value;
}
