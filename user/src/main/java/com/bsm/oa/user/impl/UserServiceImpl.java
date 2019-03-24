package com.bsm.oa.user.impl;

import static com.bsm.oa.common.util.AuthUtil.buildAuthentication;
import static com.bsm.oa.common.util.ValueObjectUtil.getValue;
import static java.util.Collections.emptySet;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.User;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.user.dao.UserRepository;
import com.bsm.oa.user.model.AwsUserToken;
import com.bsm.oa.user.service.AwsIdentityService;
import com.bsm.oa.user.service.UserService;
import java.util.Collection;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final AwsIdentityService awsIdentityService;

  @Override
  public Authentication getUserAuthentication(@NotNull User user) {

    Collection<Privilege> privileges = emptySet();
    if (!userRepository.userExists(user.getUserId())) {
      userRepository.insertUser(user);
    } else {
      privileges = userRepository.getPrivileges(user.getUserId());
    }

    return buildAuthentication(getValue(user.getUserId()), privileges);
  }

  @Override
  public AwsUserToken getOpenIdAccessToken(@Valid @NotNull UserId userId) {
    return awsIdentityService.getOpenIdAccessToken(userId);
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
}
