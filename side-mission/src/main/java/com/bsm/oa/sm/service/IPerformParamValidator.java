package com.bsm.oa.sm.service;

import static com.bsm.oa.sm.exception.SideMissionException.SIDE_MISSION_PARAM_INVALID;
import static java.util.Optional.ofNullable;

import com.bsm.oa.sm.model.PerformParam;
import com.bsm.oa.sm.model.PerformParamSymbol;
import java.util.function.Function;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IPerformParamValidator {

  /**
   * Check if rate assigned to perform parameter has allowed value
   *
   * @param param parameter definition
   * @param value rate value
   */
  void validateRateValue(@Valid @NotNull PerformParam param, @NotNull Double value);

  /**
   * @param <T> return type
   * @param param parameter definition
   * @param parser string converter
   * @return parsed available value source
   */
  default <T> T parseAvailableValueSource(@Valid @NotNull PerformParam param,
                                          @NotNull Function<String, T> parser) {
    return ofNullable(param.getAvailableValuesSource()).map(parser)
      .orElseThrow(() -> new RuntimeException(param.getSymbol() + " has invalid definition"));
  }

  /**
   * @param symbol side mission perform parameter identifier
   * @param value rate value
   * @param acceptedValues accepted values short definition
   */
  default void raiseInvalidRateValueException(@Valid @NotNull PerformParamSymbol symbol,
                                              @NotNull Double value, Object acceptedValues) {

    String details = "Provided rate value for '" + symbol + "' is " + value +
      ". Parameter accepts " + acceptedValues;

    SIDE_MISSION_PARAM_INVALID.raise(details);
  }
}
