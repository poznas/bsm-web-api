package com.bsm.oa.auth;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Headers {

  public static final String HEADER_ID_TOKEN = "X-ID-TOKEN";
  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer";

  public static final String HEADER_AWS_IDENTITY = "x-aws-identity";
  public static final String HEADER_AWS_TOKEN = "x-aws-token";

}
