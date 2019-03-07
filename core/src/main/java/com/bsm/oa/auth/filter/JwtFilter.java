package com.bsm.oa.auth.filter;

import com.bsm.oa.auth.TokenProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Slf4j
@Setter
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private static final String HEADER_AUTHORIZATION = "Authorization";
  private static final String TOKEN_PREFIX = "Bearer";

  private static final SecurityContext securityContext = SecurityContextHolder.getContext();

  private final TokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    try {
      ofNullable(request.getHeader(HEADER_AUTHORIZATION))
        .map(header -> header.replace(TOKEN_PREFIX, ""))
        .map(tokenProvider::getAuthentication)
        .ifPresent(securityContext::setAuthentication);

      filterChain.doFilter(request, response);

      resetAuthenticationAfterRequest();
    } catch (JwtException e) {
      log.info("Security - JWT Exception : " + e.getMessage());
      response.setStatus(SC_UNAUTHORIZED);
    }
  }

  private void resetAuthenticationAfterRequest() {
    securityContext.setAuthentication(null);
  }
}
