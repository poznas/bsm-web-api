package com.bsm.oa.sm.dao;

import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SideMissionRepository {

  void mergeSideMissionType(SideMissionType missionType);

  List<SideMissionType> getSideMissionTypes();

  void insertSideMissionReport(ReportSideMissionRequest request);
}
