package com.bsm.oa.points.dao;

import com.bsm.oa.points.model.Points;
import com.bsm.oa.points.model.PointsFilter;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointsRepository {

  List<Points> selectPoints(PointsFilter filter);

  long selectPointsCount(PointsFilter filter);
}
