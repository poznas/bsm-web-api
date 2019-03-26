package com.bsm.oa.common.util;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.UserId;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;

@Validated
@UtilityClass
public class AuthUtil {

  public static Authentication buildAuthentication(@Valid @NotNull UserId userId,
                                                   Collection<? extends GrantedAuthority> authorities) {
    return buildAuthentication(userId.getValue(), authorities);
  }

  public static Authentication buildAuthentication(@NotBlank String userId,
                                                   Collection<? extends GrantedAuthority> authorities) {
    var principal = new User(userId, "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }

  public static UserId userId(@NotNull Authentication authentication) {
    return UserId.of(authentication.getName());
  }

  public static boolean hasPrivilege(@NotNull Authentication auth, @NotNull Privilege privilege) {
    return auth.getAuthorities().contains(privilege);
  }
}
