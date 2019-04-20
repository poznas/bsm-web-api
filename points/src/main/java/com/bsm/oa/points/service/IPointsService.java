package com.bsm.oa.points.service;

import com.bsm.oa.common.model.TeamId;
import com.bsm.oa.points.model.Points;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPointsService {

  /**
   * @param teamId team identifier
   * @param pageable page request params
   * @return paged list of team points data
   */
  Page<Points> getTeamPoints(@Valid @NotNull TeamId teamId, @NotNull Pageable pageable);
}
