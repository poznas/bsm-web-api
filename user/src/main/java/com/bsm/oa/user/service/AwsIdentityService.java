package com.bsm.oa.user.service;

import com.bsm.oa.common.model.UserId;
import com.bsm.oa.user.model.AwsUserToken;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface AwsIdentityService {

  /**
   * Get Open ID connect token from Amazon Cognito
   *
   * @param userId user identifier
   */
  AwsUserToken getOpenIdAccessToken(@Valid @NotNull UserId userId);

}

