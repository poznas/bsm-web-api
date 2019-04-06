package com.bsm.oa.sm.model;

import com.bsm.oa.common.model.PageableFilter;
import com.bsm.oa.common.model.UserId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
public class SideMissionReportFilter extends PageableFilter {

  private final ToRateBy toRateBy;

  private final UserId raterId;

}
