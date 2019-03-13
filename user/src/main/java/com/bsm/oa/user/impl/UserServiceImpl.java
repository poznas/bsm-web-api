package com.bsm.oa.user.impl;

import com.bsm.oa.common.model.User;
import com.bsm.oa.user.service.UserService;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Override
  public Authentication getUserAuthentication(@NotNull User user) {
    return new TestingAuthenticationToken("test", "test");
  }
}
