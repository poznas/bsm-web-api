package com.bsm.oa.user.impl;

import static com.bsm.oa.common.util.ValueObjectUtil.getValue;

import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.user.model.AwsUserToken;
import com.bsm.oa.user.service.AwsIdentityService;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@RequiredArgsConstructor
public class AwsIdentityServiceImpl implements AwsIdentityService {

  private final String identityPoolId;
  private final String developerProviderName;
  private final long tokenDuration;

  private final AmazonCognitoIdentity cognitoIdentity;

  @Override
  public AwsUserToken getOpenIdAccessToken(@Valid @NotNull UserId userId) {

    var request = new GetOpenIdTokenForDeveloperIdentityRequest();
    request.setLogins(Map.of(developerProviderName, getValue(userId)));
    request.setIdentityPoolId(identityPoolId);
    request.setTokenDuration(tokenDuration);

    var response = cognitoIdentity.getOpenIdTokenForDeveloperIdentity(request);

    return AwsUserToken.of(response.getIdentityId(), response.getToken());
  }
}
