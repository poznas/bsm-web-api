package com.bsm.oa.user.impl;

import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.bsm.oa.user.service.AwsIdentityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsIdentityServiceImpl implements AwsIdentityService {

  private final AmazonCognitoIdentity cognitoIdentity;
}
