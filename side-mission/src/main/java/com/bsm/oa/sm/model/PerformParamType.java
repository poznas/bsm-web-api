package com.bsm.oa.sm.model;

import static com.bsm.oa.common.util.ObjectUtils.getOrNull;

import com.bsm.oa.sm.util.SideMissionUtils;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PerformParamType {

  BOOLEAN(null, null),
  UNLIMITED_DECIMAL(null, null),
  LIMITED_INTEGER(Integer::parseInt, null),
  STRING_PICKER(null, SideMissionUtils::parsePickerParamEntries);

  private final Function<String, Integer> getAvailableLimit;
  private final Function<String, Map<String, Double>> getAvailableEntries;

  public Integer getAvailableLimit(String source) {
    return applyParser(source, getAvailableLimit);
  }

  public Map<String, Double> getAvailableEntries(String source) {
    return applyParser(source, getAvailableEntries);
  }

  private <T> T applyParser(String valuesSource, Function<String, T> function) {
    return getOrNull(valuesSource,
      source -> getOrNull(function, fun -> fun.apply(source)));
  }
}
