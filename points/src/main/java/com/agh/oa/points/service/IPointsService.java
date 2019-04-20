package com.agh.oa.points.service;

import com.agh.oa.points.model.PointsDetails;
import com.bsm.oa.common.model.TeamId;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPointsService {

  /**
   * @param teamId team identifier
   * @param pageable page request params
   * @return paged list of team points details
   */
  Page<PointsDetails> getTeamPoints(@Valid @NotNull TeamId teamId, @NotNull Pageable pageable);
}
