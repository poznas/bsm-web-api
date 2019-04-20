package com.bsm.oa.points.impl;

import com.bsm.oa.common.model.TeamId;
import com.bsm.oa.common.util.PageUtils;
import com.bsm.oa.points.dao.PointsRepository;
import com.bsm.oa.points.model.Points;
import com.bsm.oa.points.model.PointsFilter;
import com.bsm.oa.points.service.IPointsService;
import com.bsm.oa.user.service.UserService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class PointsService implements IPointsService {

  private final UserService userService;
  private final PointsRepository pointsRepository;

  @Override
  public Page<Points> getTeamPoints(@Valid @NotNull TeamId teamId, @NotNull Pageable pageable) {
    userService.assertTeamExists(teamId);
    var filter = PointsFilter.of(teamId);

    return PageUtils.retrievePage(filter, pageable, pointsRepository::selectPoints,
      pointsRepository::selectPointsCount);
  }
}
