package com.bsm.oa.user.service;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.User;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.user.model.AwsUserToken;
import java.util.Set;
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

  /**
   * Same as {@link this#getOpenIdAccessToken(UserId)} but for context user
   */
  AwsUserToken getOpenIdAccessToken();

  /**
   * @param userId user identifier
   * @return set of privileges assigned to user
   */
  Set<Privilege> getPrivileges(@Valid @NotNull UserId userId);

  /**
   * Set user privileges
   *
   * @param userId user identifier
   * @param privileges new set of privileges
   */
  void setUserPrivileges(@Valid @NotNull UserId userId, @NotNull Set<Privilege> privileges);
}
