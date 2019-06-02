package com.bsm.oa.sm.service;

import com.bsm.oa.sm.model.PerformParamSymbol;
import com.bsm.oa.sm.model.ProofMediaLink;
import com.bsm.oa.sm.model.RaterType;
import com.bsm.oa.sm.model.SideMissionReport;
import com.bsm.oa.sm.model.SideMissionReportId;
import com.bsm.oa.sm.model.SideMissionType;
import com.bsm.oa.sm.model.SideMissionTypeID;
import com.bsm.oa.sm.request.ReportSideMissionRequest;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISideMissionService {

  /**
   * Create or replace side mission type
   *
   * @param missionType mission type data
   */
  void mergeSideMissionType(@Valid @NotNull SideMissionType missionType);

  /**
   * @return side mission type list
   */
  List<SideMissionType> getSideMissionTypes();

  /**
   * @param typeId side mission type identifier
   * @return side mission type details
   */
  SideMissionType getSideMissionType(@Valid @NotNull SideMissionTypeID typeId);

  /**
   * Create side mission report
   *
   * @param request with report details
   */
  void reportSideMission(@Valid @NotNull ReportSideMissionRequest request);

  /**
   * @param toRateBy specifies kind of rater
   * @param pageable page request params
   * @return paged list of SM reports awaiting current user's rate
   */
  Page<SideMissionReport> getSideMissionReports(@NotNull RaterType toRateBy,
                                                @NotNull Pageable pageable);

  /**
   * @param reportId side mission report identifier
   * @return side mission proof media links
   */
  List<ProofMediaLink> getReportProofs(@Valid @NotNull SideMissionReportId reportId);

  /**
   * Assign perform param rates to side mission report
   * @param reportId side mission report identifier
   * @param raterType rater type
   * @param rates map of mission perform param symbol - assigned value
   */
  void rateReport(@Valid @NotNull SideMissionReportId reportId, @NotNull RaterType raterType,
                  @Valid @NotEmpty Map<PerformParamSymbol, Double> rates);
}
