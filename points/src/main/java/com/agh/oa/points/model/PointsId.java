package com.agh.oa.points.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PointsId {

  @NotNull
  private final PointsType type;

  @Min(1)
  @NotNull
  private final Long id;

}
