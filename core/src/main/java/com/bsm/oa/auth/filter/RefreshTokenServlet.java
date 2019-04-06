package com.bsm.oa.auth.filter;

import static com.bsm.oa.auth.AuthorizationException.EMPTY_REFRESH_TOKEN;
import static com.bsm.oa.auth.AuthorizationException.INVALID_REFRESH_TOKEN;
import static com.bsm.oa.auth.Headers.HEADER_REFRESH_TOKEN;
import static java.util.Optional.ofNullable;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.service.AwsIdentityService;
import com.bsm.oa.user.service.UserService;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

@Slf4j
@WebFilter(urlPatterns = "/refresh-token")
public class RefreshTokenServlet extends ReplyWithTokensServlet {

  public RefreshTokenServlet(TokenProvider tokenProvider,
                             UserService userService,
                             AwsIdentityService awsIdentityService) {
    super(tokenProvider, userService, awsIdentityService);
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain)
    throws IOException {
    try {
      Authentication newAuth = ofNullable(request.getHeader(HEADER_REFRESH_TOKEN))
        .map(tokenProvider::getAuthentication)
        .map(userService::refreshAuthentication)
        .orElseThrow(EMPTY_REFRESH_TOKEN);

      replyWithTokens(response, newAuth);

    } catch (JwtException e) {
      log.info("Security - JWT Exception : " + e.getMessage());
      response.sendError(SC_UNAUTHORIZED, INVALID_REFRESH_TOKEN.getReason());
    }
  }

}
