package com.bsm.oa.common.service.impl;

import com.bsm.oa.common.model.UserId;
import com.bsm.oa.common.service.UserDetailsProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ContextUserDetailsProvider implements UserDetailsProvider {

  @Override
  public UserId getUserId() {
    return UserId.of(SecurityContextHolder.getContext().getAuthentication().getName());
  }
}
