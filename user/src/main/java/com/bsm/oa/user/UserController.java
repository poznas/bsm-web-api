package com.bsm.oa.user;

import static com.bsm.oa.user.UserController.USER_CONTEXT;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.User;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.user.service.UserService;
import java.util.List;
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
  private static final String USER_PRIVILEGES_PATH = "/{userId}/privileges";
  private static final String USER_MY_PRIVILEGES_PATH = "/my-privileges";
  private static final String USER_TEAMMATES_PATH = "/users/teammates";

  private final UserService userService;

  @GetMapping(USER_MY_PRIVILEGES_PATH)
  public Set<Privilege> getUserPrivileges() {
    return userService.getPrivileges();
  }

  @GetMapping(USER_PRIVILEGES_PATH)
  @PreAuthorize("hasAuthority('PRV_EDIT_USERS')")
  public Set<Privilege> getUserPrivileges(@NotBlank @PathVariable("userId") UserId userId) {
    return userService.getPrivileges(userId);
  }

  @PutMapping(USER_PRIVILEGES_PATH)
  @PreAuthorize("hasAuthority('PRV_EDIT_USERS')")
  public void setUserPrivileges(@NotBlank @PathVariable("userId") UserId userId,
                                @NotNull @RequestBody Set<Privilege> privileges) {
    userService.setUserPrivileges(userId, privileges);
  }

  @GetMapping(USER_TEAMMATES_PATH)
  public List<User> getTeammates() {
    return userService.getTeammates();
  }

}
