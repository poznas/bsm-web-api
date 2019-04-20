package com.bsm.oa.points.model;

import com.bsm.oa.common.model.PageableFilter;
import com.bsm.oa.common.model.TeamId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
public class PointsFilter extends PageableFilter {

  private final TeamId teamId;
}
