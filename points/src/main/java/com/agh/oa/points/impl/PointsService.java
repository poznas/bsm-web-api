package com.agh.oa.points.impl;

import com.agh.oa.points.model.PointsDetails;
import com.agh.oa.points.service.IPointsService;
import com.bsm.oa.common.model.TeamId;
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

  @Override
  public Page<PointsDetails> getTeamPoints(@Valid @NotNull TeamId teamId,
                                           @NotNull Pageable pageable) {
    return null;
  }
}
