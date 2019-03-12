package com.bsm.oa.config;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.filter.JwtFilter;
import com.bsm.oa.auth.filter.LoginFilter;
import com.bsm.oa.auth.service.TokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final TokenProvider tokenProvider;
  private final TokenVerifier tokenVerifier;
  private final UserDetailsService userDetailsService;

  @Bean
  public UsernamePasswordAuthenticationFilter jwtAuthFilter() {
    return new LoginFilter(userDetailsService, tokenProvider, tokenVerifier);
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
