package com.bsm.oa.sm.service;

import com.bsm.oa.sm.model.PerformParam;
import javax.validation.Valid;
import org.springframework.lang.NonNull;

public interface IPerformParamValidator {

  /**
   * Check if rate assigned to perform parameter has allowed value
   *
   * @param param parameter definition
   * @param value rate value
   */
  void validateRateValue(@Valid @NonNull PerformParam param, @NonNull Double value);
}
