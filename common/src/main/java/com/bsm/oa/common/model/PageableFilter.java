package com.bsm.oa.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PageableFilter {

  private int pageSize;

  private long offset;

}
