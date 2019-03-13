package com.bsm.oa.auth.filter;

import static java.util.Optional.ofNullable;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.service.TokenVerifier;
import com.bsm.oa.common.model.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@WebFilter(urlPatterns = "/login")
public class LoginFilter extends OncePerRequestFilter {

  private static final String HEADER_ID_TOKEN = "X-ID-TOKEN";

  private final TokenProvider tokenProvider;
  private final TokenVerifier tokenVerifier;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
    throws ServletException, IOException {
    log.debug("Apply login filter : {}", request.getServletPath());

    User user;
    try {
      user = ofNullable(request.getHeader(HEADER_ID_TOKEN))
        .map(tokenVerifier::verifyTokenId)
        .orElseThrow();
    } catch (ResponseStatusException ex) {
    }

  }
}
