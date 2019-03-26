package com.bsm.oa.auth;

import static com.bsm.oa.auth.Headers.HEADER_AUTHORIZATION;
import static com.bsm.oa.auth.Headers.HEADER_ID_TOKEN;
import static com.bsm.oa.auth.Headers.HEADER_REFRESH_TOKEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.validation.constraints.NotBlank;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("squid:MaximumInheritanceDepth")
public class AuthorizationException extends ResponseStatusException {

  public static final AuthorizationException EMPTY_PROVIDER_ID_TOKEN
    = new AuthorizationException(required(HEADER_ID_TOKEN));

  public static final AuthorizationException INVALID_GOOGLE_ID_TOKEN
    = new AuthorizationException("Google ID Token verification failure");

  public static final AuthorizationException EMPTY_REFRESH_TOKEN
    = new AuthorizationException(required(HEADER_REFRESH_TOKEN));

  public static final AuthorizationException INVALID_REFRESH_TOKEN
    = new AuthorizationException("Refresh token validation failure");

  public static final AuthorizationException EMPTY_ACCESS_TOKEN
    = new AuthorizationException(required(HEADER_AUTHORIZATION));

  public static final AuthorizationException INVALID_ACCESS_TOKEN
    = new AuthorizationException("Refresh token validation failure");

  private AuthorizationException(String reason) {
    super(UNAUTHORIZED, reason);
  }

  private static String required(@NotBlank String header) {
    return "Required " + header + " is empty";
  }
}
