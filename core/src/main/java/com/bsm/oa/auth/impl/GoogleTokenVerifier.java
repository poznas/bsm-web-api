package com.bsm.oa.auth.impl;

import static com.bsm.oa.auth.AuthorizationException.INVALID_GOOGLE_ID_TOKEN;
import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang.StringUtils.substring;

import com.bsm.oa.auth.service.TokenVerifier;
import com.bsm.oa.common.model.User;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.common.model.Username;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GoogleTokenVerifier implements TokenVerifier {

  private static final List<String> issuers = asList("https://accounts.google.com",
    "accounts.google.com");

  @Override
  public User verifyTokenId(@NotBlank String tokenId) {
    return Optional.of(tokenId)
      .map(this::verifyGoogleTokenId)
      .map(GoogleIdToken::getPayload)
      .map(this::buildUser)
      .orElseThrow();
  }

  private GoogleIdToken verifyGoogleTokenId(String tokenId) {

    var verifier = new GoogleIdTokenVerifier
      .Builder(new NetHttpTransport(), new JacksonFactory())
      .setIssuers(issuers)
      .build();

    try {
      GoogleIdToken token = verifier.verify(tokenId);
      if(nonNull(token)) {
        return token;
      }
    } catch (GeneralSecurityException | IOException e) {
      log.error(e.getMessage());
    }
    throw INVALID_GOOGLE_ID_TOKEN;
  }

  private User buildUser(Payload payload) {
    return User.builder()
      .username(buildUsername(payload))
      .userId(UserId.of(payload.getSubject()))
      .imageUrl((String) payload.get("picture"))
      .email(payload.getEmail())
      .build();
  }

  private Username buildUsername(Payload payload) {
    return ofNullable(payload)
      .map(body -> (String) body.get("name"))
      .map(name -> substring(name, 0, Username.MAX_SIZE))
      .map(Username::of)
      .orElse(null);
  }
}
