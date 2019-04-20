package com.bsm.oa.user.impl;

import static com.bsm.oa.common.util.AuthUtil.buildAuthentication;
import static com.bsm.oa.common.util.AuthUtil.userId;
import static java.util.Collections.emptySet;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.TeamId;
import com.bsm.oa.common.model.User;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.common.service.UserDetailsProvider;
import com.bsm.oa.user.dao.UserRepository;
import com.bsm.oa.user.service.UserService;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserDetailsProvider userDetailsProvider;

  @Override
  public Authentication getUserAuthentication(@NotNull User user) {

    Collection<Privilege> privileges = emptySet();
    if (!userRepository.userExists(user.getUserId())) {
      userRepository.insertUser(user);
    } else {
      privileges = userRepository.getPrivileges(user.getUserId());
    }

    return buildAuthentication(user.getUserId(), privileges);
  }

  @Override
  public Authentication refreshAuthentication(@NotNull Authentication authentication) {
    UserId userId = userId(authentication);

    var privileges = userRepository.getPrivileges(userId);

    return buildAuthentication(userId, privileges);
  }

  @Override
  public Set<Privilege> getPrivileges() {
    return getPrivileges(userDetailsProvider.getUserId());
  }

  @Override
  public Set<Privilege> getPrivileges(@Valid @NotNull UserId userId) {
    return userRepository.getPrivileges(userId);
  }

  @Override
  public void setUserPrivileges(@Valid @NotNull UserId userId, @NotNull Set<Privilege> privileges) {
    userRepository.clearPrivileges(userId);
    userRepository.addPrivileges(userId, privileges);
  }

  @Override
  public List<User> getTeammates() {
    return userRepository.getTeammates(userDetailsProvider.getUserId());
  }

  @Override
  public void assertUserExists(@Valid @NotNull UserId userId, @NotBlank String who) {
    if (!userRepository.userExists(userId)) {
      throw new ResponseStatusException(BAD_REQUEST, who + " does not exists");
    }
  }

  @Override
  public void assertTeamExists(@Valid @NotNull TeamId teamId) {
    if (!userRepository.teamExists(teamId)) {
      throw new ResponseStatusException(BAD_REQUEST, "team " + teamId + " does not exists");
    }
  }
}
