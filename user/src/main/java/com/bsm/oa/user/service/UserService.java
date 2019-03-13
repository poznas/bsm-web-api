package com.bsm.oa.user.service;

import com.bsm.oa.common.model.User;
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
}
