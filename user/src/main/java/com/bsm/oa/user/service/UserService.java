package com.bsm.oa.user.service;

import com.bsm.oa.common.model.User;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.user.model.AwsUserToken;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;

public interface UserService {

  /**
   * Retrieve user authentication object with privileges
   *
   * @param user user data retrieved from external auth provider
   * @return authentication @see {@link Authentication}
   */
  Authentication getUserAuthentication(@NotNull User user);

  /**
   * Get Open ID connect token from Amazon Cognito
   *
   * @param userId user identifier
   * @return null if user is not privileged enough
   */
  AwsUserToken getOpenIdAccessToken(@Valid @NotNull UserId userId);
}
