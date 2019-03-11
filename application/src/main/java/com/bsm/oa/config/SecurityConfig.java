package com.bsm.oa.config;

import com.bsm.oa.auth.TokenProvider;
import com.bsm.oa.auth.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AuthenticationProvider authenticationProvider;

  private final TokenProvider tokenProvider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider);
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
