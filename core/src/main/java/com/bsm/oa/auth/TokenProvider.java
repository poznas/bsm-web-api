package com.bsm.oa.auth;

import com.google.api.client.util.Joiner;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

import static com.bsm.common.util.CollectionUtils.mapList;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Slf4j
@Component
public class TokenProvider {

  private static final String AUTHORITIES_KEY = "auth";

  @Value("${spring.security.authentication.jwt.validity}")
  private long tokenValidityInMilliSeconds;

  @Value("${spring.security.authentication.jwt.secret}")
  private String secret;

  public String createToken(Authentication authentication) {

    String authorities = Joiner.on(',')
      .join(mapList(authentication.getAuthorities(), GrantedAuthority::getAuthority));

    var now = ZonedDateTime.now();
    var expirationDateTime = now.plus(this.tokenValidityInMilliSeconds, MILLIS);

    var issueDate = Date.from(now.toInstant());
    var expirationDate = Date.from(expirationDateTime.toInstant());

    return Jwts.builder()
      .signWith(HS512, secret)
      .setSubject(authentication.getName())
      .claim(AUTHORITIES_KEY, authorities)
      .setExpiration(expirationDate)
      .setIssuedAt(issueDate)
      .compact();
  }

  public Authentication getAuthentication(String token) {

    var claims = Jwts.parser().setSigningKey(secret)
      .parseClaimsJws(token).getBody();

    Collection<GrantedAuthority> authorities =
      stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(toList());

    var principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }
}
