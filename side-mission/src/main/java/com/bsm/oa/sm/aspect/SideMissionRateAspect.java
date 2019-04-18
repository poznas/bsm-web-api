package com.bsm.oa.sm.aspect;

import static com.bsm.oa.common.util.ValueObjectUtil.getValue;
import static java.util.Objects.isNull;
import static java.util.Optional.of;

import com.bsm.oa.sm.dao.SideMissionRepository;
import com.bsm.oa.sm.model.ReportRateData;
import com.bsm.oa.sm.model.SideMissionReportId;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Aspect
@Component
@Validated
@RequiredArgsConstructor
public class SideMissionRateAspect {

  private static final ScriptEngine SCRIPT_ENGINE
    = new ScriptEngineManager().getEngineByName("JavaScript");

  private final SideMissionRepository sideMissionRepository;

  @AfterReturning("execution(public void com.bsm.oa.sm..rateReport(..)) && args(reportId,..)")
  public void evaluateReportRates(SideMissionReportId reportId) {
    log.info("Attempt to evaluate side mission report[{}] points", reportId);

    of(reportId).map(sideMissionRepository::getReportRateData)
      .ifPresent(this::evaluateReportRates);
  }

  private void evaluateReportRates(@Valid @NotNull ReportRateData rateData) {
    log.info("Evaluating rates for report[{}]", rateData.getReportId());

    rateData.getParamRates().stream()
      .filter(rate -> isNull(rate.getAssignedValue())).findAny()
      .ifPresentOrElse(rate -> log.info("{} is not satisfied", rate.getSymbol()),
        () -> insertReportPoints(rateData));
  }

  private void insertReportPoints(@Valid @NotNull ReportRateData rateData) {
    log.info("Counting report[{}] points", rateData.getReportId());

    String equation = getValue(rateData.getEquation());

    for (var rate : rateData.getParamRates()) {
      String target = "<" + rate.getSymbol() + ">";
      equation = equation.replaceAll(target, rate.getAssignedValue().toString());
    }

    try {
      Number result = (Number) SCRIPT_ENGINE.eval(equation);

      Double points = of(result)
        .filter(Integer.class::isInstance)
        .map(Integer.class::cast)
        .map(x -> x  * 1.0)
        .orElse(result.doubleValue());

      log.info("Merging {} points for report[{}]", points, rateData.getReportId());
      sideMissionRepository.mergeReportPoints(rateData.getReportId(), points);
    } catch (ScriptException | ClassCastException e) {
      log.error("Error while eval points equation: {}", e.getMessage());
    }
  }
}
