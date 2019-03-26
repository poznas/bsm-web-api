package com.bsm.oa.auth.service;

import com.bsm.oa.user.model.AwsUserToken;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;

public interface AwsIdentityService {

  /**
   * Get Open ID connect token from Amazon Cognito
   *
   * @param authentication user data with privileges
   */
  AwsUserToken getOpenIdAccessToken(@NotNull Authentication authentication);

}

