package com.bsm.oa.points;

import static com.bsm.oa.points.PointsController.CONTEXT;

import com.bsm.oa.common.model.TeamId;
import com.bsm.oa.points.model.Points;
import com.bsm.oa.points.service.IPointsService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(CONTEXT)
@RequiredArgsConstructor
public class PointsController {

  static final String CONTEXT = "/points";
  private static final String TEAM_POINTS_PATH = "/team/{teamId}";

  private final IPointsService pointsService;

  @GetMapping(TEAM_POINTS_PATH)
  Page<Points> getTeamPoints(@PageableDefault Pageable pageable,
                             @Valid @NotNull @PathVariable("teamId") TeamId teamId) {
    return pointsService.getTeamPoints(teamId, pageable);
  }

}
