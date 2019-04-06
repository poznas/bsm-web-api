package com.bsm.oa.auth.filter;

import static com.bsm.oa.auth.AuthorizationException.EMPTY_PROVIDER_ID_TOKEN;
import static com.bsm.oa.auth.Headers.HEADER_ID_TOKEN;
import static java.util.Optional.ofNullable;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.service.AwsIdentityService;
import com.bsm.oa.auth.service.TokenVerifier;
import com.bsm.oa.common.model.User;
import com.bsm.oa.user.service.UserService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@WebFilter(urlPatterns = "/login")
public class LoginServlet extends ReplyWithTokensServlet {

  private final TokenVerifier tokenVerifier;

  public LoginServlet(TokenProvider tokenProvider,
                      UserService userService,
                      AwsIdentityService awsIdentityService,
                      TokenVerifier tokenVerifier) {
    super(tokenProvider, userService, awsIdentityService);
    this.tokenVerifier = tokenVerifier;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain)
    throws IOException {
    try {
      var idToken = ofNullable(request.getHeader(HEADER_ID_TOKEN))
        .orElseThrow(EMPTY_PROVIDER_ID_TOKEN);

      User user = tokenVerifier.verifyTokenId(idToken);
      Authentication authentication = userService.getUserAuthentication(user);

      replyWithTokens(response, authentication);

    } catch (ResponseStatusException ex) {
      response.sendError(ex.getStatus().value(), ex.getReason());
    }
  }
}
