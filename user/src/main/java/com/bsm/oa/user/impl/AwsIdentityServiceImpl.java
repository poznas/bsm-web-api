package com.bsm.oa.user.impl;

import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.bsm.oa.user.service.AwsIdentityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AwsIdentityServiceImpl implements AwsIdentityService {

  private final String identityPoolId;
  private final String developerProviderName;

  private final AmazonCognitoIdentity cognitoIdentity;
}
