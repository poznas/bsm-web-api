package com.bsm.oa.user.service;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.TeamId;
import com.bsm.oa.common.model.User;
import com.bsm.oa.common.model.UserId;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
   * Retrieve user authentication object with privileges
   *
   * @param authentication with user ID from refresh token
   * @return authentication @see {@link Authentication}
   */
  Authentication refreshAuthentication(@NotNull Authentication authentication);

  /**
   * @return set of privileges assigned to current context user
   */
  Set<Privilege> getPrivileges();

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

  /**
   * @return users assigned to the same team as the current user
   */
  List<User> getTeammates();

  /**
   * Throws BAD REQUEST exception if user does not exists
   *
   * @param userId user identifier
   * @param who message prefix specifying request field
   */
  void assertUserExists(@Valid @NotNull UserId userId, @NotBlank String who);

  /**
   * Throws BAD REQUEST exception if team does not exists
   *
   * @param teamId team identifier
   */
  void assertTeamExists(@Valid @NotNull TeamId teamId);
}
