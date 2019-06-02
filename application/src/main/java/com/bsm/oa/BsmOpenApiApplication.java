package com.bsm.oa;

import com.bsm.oa.config.Secrets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableFeignClients
@ServletComponentScan
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableConfigurationProperties(Secrets.class)
public class BsmOpenApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BsmOpenApiApplication.class, args);
  }

}

