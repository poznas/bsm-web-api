package com.bsm.oa.user.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class AwsUserToken {

  /**
   * AWS Identity ID
   */
  @NotBlank
  private String identityId;

  /**
   * AWS OpenID connect token
   */
  @NotBlank
  private String token;

}
