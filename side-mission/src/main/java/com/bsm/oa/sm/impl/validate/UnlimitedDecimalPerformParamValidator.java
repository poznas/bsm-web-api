package com.bsm.oa.sm.impl.validate;

import static com.bsm.oa.sm.model.PerformParamType.UNLIMITED_DECIMAL;

import com.bsm.oa.sm.annotation.PerformParamValidator;
import com.bsm.oa.sm.model.PerformParam;
import com.bsm.oa.sm.service.IPerformParamValidator;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@PerformParamValidator(UNLIMITED_DECIMAL)
public class UnlimitedDecimalPerformParamValidator implements IPerformParamValidator {

  @Override
  public void validateRateValue(@Valid @NotNull PerformParam param, @NotNull Double value) {
    if (value < 0) {
      raiseInvalidRateValueException(param.getSymbol(), value, "positive numbers");
    }
  }
}
