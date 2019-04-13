package com.bsm.oa.sm.model;

public enum RaterType {
  JUDGE,
  PROFESSOR;

  public RaterType getOther() {
    return this == JUDGE ? PROFESSOR : JUDGE;
  }
}
