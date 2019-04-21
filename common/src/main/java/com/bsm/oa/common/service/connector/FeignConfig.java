package com.bsm.oa.common.service.connector;

import feign.Logger;
import feign.Logger.Level;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class FeignConfig {

  @Bean
  Logger.Level feignLoggerLevel() {
    return Level.FULL;
  }
}
