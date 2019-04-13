package com.bsm.oa.sm.impl.validate;

import static com.bsm.oa.sm.model.PerformParamType.LIMITED_INTEGER;
import static com.google.common.math.DoubleMath.isMathematicalInteger;

import com.bsm.oa.sm.annotation.PerformParamValidator;
import com.bsm.oa.sm.model.PerformParam;
import com.bsm.oa.sm.service.IPerformParamValidator;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@PerformParamValidator(LIMITED_INTEGER)
public class LimitedIntegerPerformParamValidator implements IPerformParamValidator {

  @Override
  public void validateRateValue(@Valid @NotNull PerformParam param, @NotNull Double value) {
    Integer limit = parseAvailableValueSource(param, Integer::parseInt);

    if (!isMathematicalInteger(value) || value < 0 || limit < value) {
      raiseInvalidRateValueException(param.getSymbol(), value, "integers from range 0.." + limit);
    }
  }
}
