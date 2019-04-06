package com.bsm.oa.auth.filter;

import static com.bsm.oa.auth.AuthorizationException.EMPTY_ACCESS_TOKEN;
import static com.bsm.oa.auth.AuthorizationException.INVALID_ACCESS_TOKEN;
import static com.bsm.oa.auth.Headers.HEADER_AUTHORIZATION;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import com.bsm.oa.auth.TokenProvider;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Setter
@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {

  private static final Consumer<Authentication> setSecurityContextAuthentication = auth ->
    SecurityContextHolder.getContext().setAuthentication(auth);

  private AntPathMatcher matcher = new AntPathMatcher();

  private final TokenProvider tokenProvider;
  private final String[] whiteList;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain)
    throws ServletException, IOException {
    try {
      String accessToken = ofNullable(request.getHeader(HEADER_AUTHORIZATION))
        .orElseThrow(EMPTY_ACCESS_TOKEN);

      of(accessToken).map(tokenProvider::getAuthentication)
        .ifPresent(setSecurityContextAuthentication);

      filterChain.doFilter(request, response);

      setSecurityContextAuthentication.accept(null);
    } catch (JwtException e) {
      log.info("Security - JWT Exception : " + e.getMessage());
      response.sendError(SC_UNAUTHORIZED, INVALID_ACCESS_TOKEN.getReason());
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return Stream.of(whiteList)
      .anyMatch(pattern -> matcher.match(pattern, request.getServletPath()));
  }
}
