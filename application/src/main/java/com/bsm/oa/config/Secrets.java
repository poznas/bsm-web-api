package com.bsm.oa.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@NoArgsConstructor
@ConfigurationProperties("bsm")
public class Secrets {

  private String jwtSecret;

  private String awsAccessKey;

  private String awsSecretKey;

}
