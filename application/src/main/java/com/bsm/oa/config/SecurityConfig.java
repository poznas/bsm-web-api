package com.bsm.oa.config;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.filter.AccessTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final Secrets secrets;

  private static final String[] WHITE_LIST =
    {"/login",
      "/refresh-token",
      "/v2/api-docs",
      "**/configuration/ui",
      "/swagger-resources/**",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.addFilterBefore(new AccessTokenFilter(tokenProvider(), WHITE_LIST),
      UsernamePasswordAuthenticationFilter.class);
    http.authorizeRequests()
      .antMatchers(WHITE_LIST).permitAll()
      .anyRequest()
      .authenticated();
    http.csrf().disable();
  }

  @Bean
  public TokenProvider tokenProvider() {
    return new TokenProvider(secrets.getJwtSecret());
  }
}
