package com.bsm.oa.auth.filter;

import static java.util.Optional.ofNullable;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.service.TokenVerifier;
import com.bsm.oa.common.model.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

  private static final String HEADER_ID_TOKEN = "X-ID-TOKEN";

  private final UserDetailsService userDetailsService;
  private final TokenProvider tokenProvider;
  private final TokenVerifier tokenVerifier;


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response)
    throws AuthenticationException {

    log.debug("Apply login filter : {}", request.getServletPath());

    User user;
    try {
      user = ofNullable(request.getHeader(HEADER_ID_TOKEN))
        .map(tokenVerifier::verifyTokenId)
        .orElseThrow();
    } catch (ResponseStatusException ex) {
      return null;
    }

    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult)
    throws IOException, ServletException {
    super.successfulAuthentication(request, response, chain, authResult);
    log.debug("Authentication Success");
  }
}
