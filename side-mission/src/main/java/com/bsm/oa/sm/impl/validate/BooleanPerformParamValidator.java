package com.bsm.oa.sm.impl.validate;

import static com.bsm.oa.common.util.CollectionUtils.mapSet;
import static com.bsm.oa.sm.model.PerformParamType.BOOLEAN;

import com.bsm.oa.sm.annotation.PerformParamValidator;
import com.bsm.oa.sm.model.PerformParam;
import com.bsm.oa.sm.service.IPerformParamValidator;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@PerformParamValidator(BOOLEAN)
public class BooleanPerformParamValidator implements IPerformParamValidator {

  private static final Set<Double> acceptedDoubles = Set.of(0.0, 1.0);
  private static final Set<Integer> acceptedInts = mapSet(acceptedDoubles, Double::intValue);

  @Override
  public void validateRateValue(@Valid @NotNull PerformParam param, @NotNull Double value) {
    if (!acceptedDoubles.contains(value)) {
      raiseInvalidRateValueException(param.getSymbol(), value, acceptedInts);
    }
  }
}
