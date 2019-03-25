package com.bsm.oa.common.service;

import com.bsm.oa.common.model.UserId;

public interface UserDetailsProvider {

  /**
   * @return current context user identifier
   */
  UserId getUserId();
}
