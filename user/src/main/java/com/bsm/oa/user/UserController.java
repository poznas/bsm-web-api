package com.bsm.oa.user;

import static com.bsm.oa.user.UserController.USER_CONTEXT;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.user.model.AwsUserToken;
import com.bsm.oa.user.service.UserService;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("squid:S1075")

@RestController
@RequestMapping(USER_CONTEXT)
@RequiredArgsConstructor
public class UserController {

  static final String USER_CONTEXT = "/user";
  private static final String USER_AWS_TOKEN_PATH = "/aws-token";
  private static final String USER_PRIVILEGES_PATH = "/{userId}/privileges";

  private final UserService userService;

  @GetMapping(USER_AWS_TOKEN_PATH)
  public AwsUserToken getAwsAccessToken() {
    return userService.getOpenIdAccessToken();
  }

  @GetMapping(USER_PRIVILEGES_PATH)
  public Set<Privilege> getUserPrivileges(@NotBlank @PathVariable("userId") UserId userId) {
    return userService.getPrivileges(userId);
  }

  @PutMapping(USER_PRIVILEGES_PATH)
  @PreAuthorize("hasAuthority('PRV_EDIT_USERS')")
  public void setUserPrivileges(@NotBlank @PathVariable("userId") UserId userId,
                                @NotNull @RequestBody Set<Privilege> privileges) {
    userService.setUserPrivileges(userId, privileges);
  }

}
