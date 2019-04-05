package com.bsm.oa.sm.model;

import com.bsm.oa.common.model.AwsS3Url;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProofMediaLink {

  /**
   * AWS S3 bucket proof location
   */
  @Valid
  @NotNull
  private AwsS3Url awsS3Url;

  /**
   * proof media type
   */
  @NotNull
  private ProofMediaType type;
}
