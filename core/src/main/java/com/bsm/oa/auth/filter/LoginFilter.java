package com.bsm.oa.auth.filter;

import com.bsm.common.model.User;
import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.service.TokenVerifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

  private static final String HEADER_ID_TOKEN = "X-ID-TOKEN";

  private final UserDetailsService userDetailsService;
  private final TokenProvider tokenProvider;
  private final TokenVerifier tokenVerifier;


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    log.debug("Apply login filter : {}", request.getServletPath());

    Optional<User> user;
    try {
      user = ofNullable(request.getHeader(HEADER_ID_TOKEN))
        .map(tokenVerifier::verifyTokenId);
    } catch (ResponseStatusException ex) {
      return null;
    }

    return null;
  }
}
