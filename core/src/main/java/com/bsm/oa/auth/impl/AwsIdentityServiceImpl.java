package com.bsm.oa.auth.impl;

import static com.bsm.oa.common.constant.Privilege.PRV_AWS_RESOURCE_ACCESS;
import static com.bsm.oa.common.util.AuthUtil.hasPrivilege;

import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest;
import com.bsm.oa.auth.service.AwsIdentityService;
import com.bsm.oa.user.model.AwsUserToken;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
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
  public AwsUserToken getOpenIdAccessToken(@NotNull Authentication auth) {

    if(!hasPrivilege(auth, PRV_AWS_RESOURCE_ACCESS)) {
      return null;
    }

    var request = new GetOpenIdTokenForDeveloperIdentityRequest();
    request.setLogins(Map.of(developerProviderName, auth.getName()));
    request.setIdentityPoolId(identityPoolId);
    request.setTokenDuration(tokenDuration);

    var response = cognitoIdentity.getOpenIdTokenForDeveloperIdentity(request);

    return AwsUserToken.of(response.getIdentityId(), response.getToken());
  }
}
