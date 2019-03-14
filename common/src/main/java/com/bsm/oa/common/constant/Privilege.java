package com.bsm.oa.common.constant;

import org.springframework.security.core.GrantedAuthority;

public enum Privilege implements GrantedAuthority {

  //Allows user to report side mission
  PRV_REPORT_SM,

  //Allows user to add main competition points
  PRV_ADD_MC_POINTS,

  //Allows user to add bet points
  PRV_ADD_BET_POINTS,

  //Allows user to add medal points
  PRV_ADD_MEDAL_POINTS,

  //Allows user to judge side missions
  PRV_JUDGE_SM,

  //Allows user to manage other users
  PRV_EDIT_USERS,

  //Allow user to manage master switch, which enables reporting and adding points
  PRV_MASTER_LOCK,
  ;

  @Override
  public String getAuthority() {
    return name();
  }
}
