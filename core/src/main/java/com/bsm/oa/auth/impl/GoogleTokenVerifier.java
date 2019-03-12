package com.bsm.oa.auth.impl;

import static java.util.Arrays.asList;

import com.bsm.oa.auth.service.TokenVerifier;
import com.bsm.oa.common.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class GoogleTokenVerifier implements TokenVerifier {


    private static final List<String> issuers = asList("https://accounts.google.com", "accounts.google.com");

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
            return verifier.verify(tokenId);
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    private User buildUser(Payload payload) {
        return new User();
    }
}
