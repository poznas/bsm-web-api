package com.bsm.oa.auth.filter;

import static com.bsm.oa.auth.Headers.HEADER_AUTHORIZATION;
import static com.bsm.oa.auth.Headers.HEADER_AWS_IDENTITY;
import static com.bsm.oa.auth.Headers.HEADER_AWS_TOKEN;
import static com.bsm.oa.auth.Headers.HEADER_REFRESH_TOKEN;
import static java.util.Optional.of;
import static org.springframework.http.HttpStatus.OK;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.service.AwsIdentityService;
import com.bsm.oa.user.service.UserService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
abstract class ReplyWithTokensServlet extends OncePerRequestFilter {

  final TokenProvider tokenProvider;
  final UserService userService;
  private final AwsIdentityService awsIdentityService;

  void replyWithTokens(@NonNull HttpServletResponse response,
                       @NonNull Authentication authentication) {

    String accessToken = tokenProvider.createAccessToken(authentication);
    String refreshToken = tokenProvider.createRefreshToken(authentication);
    response.addHeader(HEADER_AUTHORIZATION, accessToken);
    response.addHeader(HEADER_REFRESH_TOKEN, refreshToken);

    of(authentication)
      .map(awsIdentityService::getOpenIdAccessToken)
      .ifPresent(token -> {
        response.addHeader(HEADER_AWS_IDENTITY, token.getIdentityId());
        response.addHeader(HEADER_AWS_TOKEN, token.getToken());
      });

    response.setStatus(OK.value());
  }

}
