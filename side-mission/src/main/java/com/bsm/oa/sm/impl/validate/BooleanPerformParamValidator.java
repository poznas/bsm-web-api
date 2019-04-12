package com.bsm.oa.sm.impl.validate;

import static com.bsm.oa.sm.model.PerformParamType.BOOLEAN;

import com.bsm.oa.sm.annotation.PerformParamValidator;
import com.bsm.oa.sm.model.PerformParam;
import com.bsm.oa.sm.service.IPerformParamValidator;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Component
@PerformParamValidator(BOOLEAN)
public class BooleanPerformParamValidator implements IPerformParamValidator {

  @Override
  public void validateRateValue(@Valid @NonNull PerformParam param, @NonNull Double value) {

  }
}
