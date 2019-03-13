package com.bsm.oa.common.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  /**
   * User identifier
   */
  @Valid
  @NotNull
  private UserId id;

  /**
   * Username
   */
  @Valid
  @NotNull
  private Username username;

  /**
   * e-mail address
   */
  private String email;

  /**
   * image URL address
   */
  private String imageUrl;

  @Valid
  @NotNull
  private TeamId teamId;
}
