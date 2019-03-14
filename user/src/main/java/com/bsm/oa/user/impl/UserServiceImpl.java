package com.bsm.oa.user.impl;

import static com.bsm.oa.common.util.AuthUtil.buildAuthentication;
import static com.bsm.oa.common.util.ValueObjectUtil.getValue;
import static java.util.Collections.emptySet;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.User;
import com.bsm.oa.user.dao.UserRepository;
import com.bsm.oa.user.service.UserService;
import java.util.Collection;
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
}
