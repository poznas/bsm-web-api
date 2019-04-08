package com.bsm.oa.sm.dao;

import com.bsm.oa.common.model.UserId;
import com.bsm.oa.sm.model.PerformParamSymbol;
import com.bsm.oa.sm.model.SideMissionReport;
import com.bsm.oa.sm.model.SideMissionReportFilter;
import com.bsm.oa.sm.model.SideMissionReportId;
import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.model.SideMissionTypeID;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SideMissionRepository {

  void mergeSideMissionType(SideMissionType missionType);

  SideMissionType getSideMissionType(SideMissionTypeID typeId);

  List<SideMissionType> getSideMissionTypes();

  void insertSideMissionReport(ReportSideMissionRequest request);

  SideMissionReport selectReport(SideMissionReportId reportId);

  List<SideMissionReport> selectReports(SideMissionReportFilter filter);

  long selectReportsCount(SideMissionReportFilter filter);

  boolean hasRatedReport(@Param("userId") UserId userId,
                         @Param("reportId") SideMissionReportId reportId);

  void insertReportRate(@Param("userId") UserId userId,
                        @Param("reportId") SideMissionReportId reportId,
                        @Param("rates") Map<PerformParamSymbol, Double> rates);
}
