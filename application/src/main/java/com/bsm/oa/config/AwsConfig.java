package com.bsm.oa.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.bsm.oa.user.impl.AwsIdentityServiceImpl;
import com.bsm.oa.user.service.AwsIdentityService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@RequiredArgsConstructor
public class AwsConfig {

  private final Secrets secrets;

  @Value("${aws.region}")
  private String region;

  @Bean
  public AWSCredentials awsCredentials() {
    return new BasicAWSCredentials(secrets.getAwsAccessKey(), secrets.getAwsSecretKey());
  }

  @Bean
  public AmazonCognitoIdentity amazonCognitoIdentity() {
    var credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials());

    return AmazonCognitoIdentityClient.builder()
      .withCredentials(credentialsProvider)
      .withRegion(Regions.fromName(region))
      .build();
  }

  @Bean
  public AwsIdentityService awsIdentityService() {
    return new AwsIdentityServiceImpl(secrets.getAwsIdentityPoolId(),
      secrets.getAwsIdentityDeveloperProviderName(), amazonCognitoIdentity());
  }

}
