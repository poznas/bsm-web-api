package com.bsm.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BsmOpenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BsmOpenApiApplication.class, args);
	}

}

