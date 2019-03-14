package com.bsm.oa.common.util;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@UtilityClass
public class AuthUtil {

  public static Authentication buildAuthentication(@NotBlank String userId,
                                                   Collection<? extends GrantedAuthority> authorities) {
    var principal = new User(userId, "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }
}
