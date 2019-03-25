package com.bsm.oa.auth;

import static com.bsm.oa.common.util.AuthUtil.buildAuthentication;
import static com.bsm.oa.common.util.CollectionUtils.mapList;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import com.google.api.client.util.Joiner;
import io.jsonwebtoken.Jwts;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Slf4j
@RequiredArgsConstructor
public class TokenProvider {

  private static final String AUTHORITIES_KEY = "auth";
  private static final long REFRESH_TOKEN_EXPIRE = 864_000_000;
  private static final long ACCESS_TOKEN_EXPIRE = 300_000;

  private final String jwtSecret;

  public String createAccessToken(Authentication authentication) {

    String authorities = Joiner.on(',')
      .join(mapList(authentication.getAuthorities(), GrantedAuthority::getAuthority));

    return buildToken(authentication, ACCESS_TOKEN_EXPIRE, authorities);
  }

  public String createRefreshToken(Authentication authentication) {
    return buildToken(authentication, REFRESH_TOKEN_EXPIRE, "");
  }

  private String buildToken(Authentication authentication, long expiration, String authorities) {

    var now = ZonedDateTime.now();
    var expirationDateTime = now.plus(expiration, MILLIS);

    var issueDate = Date.from(now.toInstant());
    var expirationDate = Date.from(expirationDateTime.toInstant());

    return Jwts.builder()
      .signWith(HS512, jwtSecret)
      .setSubject(authentication.getName())
      .claim(AUTHORITIES_KEY, authorities)
      .setExpiration(expirationDate)
      .setIssuedAt(issueDate)
      .compact();
  }

  public Authentication getAuthentication(String accessKey) {

    var claims = Jwts.parser().setSigningKey(jwtSecret)
      .parseClaimsJws(accessKey).getBody();

    Collection<GrantedAuthority> authorities =
      stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(toList());

    return buildAuthentication(claims.getSubject(), authorities);
  }
}
