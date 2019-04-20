package com.bsm.oa.points.service;

import com.bsm.oa.common.model.TeamId;
import com.bsm.oa.points.model.Points;
import com.bsm.oa.points.model.TeamScore;
import java.util.List;
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

  /**
   * Score table supplier
   *
   * @return total team scores
   */
  List<TeamScore> getTotalTeamScores();
}
