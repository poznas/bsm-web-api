package com.bsm.oa.sm.dao;

import com.bsm.oa.sm.model.SideMissionType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SideMissionRepository {

  void mergeSideMissionType(SideMissionType missionType);
}
