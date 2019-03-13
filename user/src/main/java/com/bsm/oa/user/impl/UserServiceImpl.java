package com.bsm.oa.user.impl;

import com.bsm.oa.common.model.User;
import com.bsm.oa.user.service.UserService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Override
  public Authentication getUserAuthentication(@Valid @NotNull User user) {
    return new TestingAuthenticationToken("test", "test");
  }
}
