package com.bsm.oa.config;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.filter.JwtFilter;
import com.bsm.oa.auth.filter.LoginFilter;
import com.bsm.oa.auth.service.TokenVerifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final TokenProvider tokenProvider;
  private final TokenVerifier tokenVerifier;

  @Bean
  public UsernamePasswordAuthenticationFilter jwtAuthFilter() throws Exception {
    LoginFilter loginFilter = new LoginFilter(tokenProvider, tokenVerifier, authenticationManager());
    AuthenticationManager authenticationManager = authenticationManager();
    loginFilter.setAuthenticationManager(authenticationManager);
    return loginFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

    // @formatter:off
    http.authorizeRequests()
      .antMatchers("/swagger-ui.html").permitAll()
      .antMatchers("/v2/api-docs").permitAll()
      .anyRequest().fullyAuthenticated();
    // @formatter:on
    http.csrf().disable();
  }
}
