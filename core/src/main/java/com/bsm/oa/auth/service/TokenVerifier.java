package com.bsm.oa.auth.service;

import com.bsm.common.model.User;

import javax.validation.constraints.NotBlank;

public interface TokenVerifier {

  /**
   * Validates ID token against external credential provider
   * @param tokenId authentication provider success response ID token
   * @return user data
   */
  User verifyTokenId(@NotBlank String tokenId);
}
