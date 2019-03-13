package com.bsm.oa.auth.filter;

import static com.bsm.oa.auth.Headers.HEADER_AUTHORIZATION;
import static com.bsm.oa.auth.Headers.HEADER_ID_TOKEN;
import static com.bsm.oa.auth.Headers.TOKEN_PREFIX;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.service.TokenVerifier;
import com.bsm.oa.common.model.User;
import com.bsm.oa.user.service.UserService;
import java.io.IOException;
import java.util.function.Supplier;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@WebFilter(urlPatterns = "/login")
public class LoginFilter extends OncePerRequestFilter {

  private static final Supplier<ResponseStatusException> EMPTY_ID_TOKEN_HEADER =
    () -> new ResponseStatusException(BAD_REQUEST, HEADER_ID_TOKEN + " cannot be empty");

  private final TokenProvider tokenProvider;
  private final TokenVerifier tokenVerifier;
  private final UserService userService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain)
    throws ServletException, IOException {

    try {
      var idToken = ofNullable(request.getHeader(HEADER_ID_TOKEN))
        .orElseThrow(EMPTY_ID_TOKEN_HEADER);

      User user = tokenVerifier.verifyTokenId(idToken);
      Authentication authentication = userService.getUserAuthentication(user);

      String bearerToken = tokenProvider.createToken(authentication);
      response.addHeader(HEADER_AUTHORIZATION, TOKEN_PREFIX + " " + bearerToken);

      response.setStatus(OK.value());

    } catch (ResponseStatusException ex) {
      response.sendError(ex.getStatus().value(), ex.getReason());
    }
  }
}
