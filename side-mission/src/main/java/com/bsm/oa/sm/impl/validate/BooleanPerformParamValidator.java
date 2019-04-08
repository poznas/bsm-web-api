package com.bsm.oa.sm.impl.validate;

import static com.bsm.oa.sm.model.PerformParamType.BOOLEAN;

import com.bsm.oa.sm.annotation.PerformParamValidator;
import com.bsm.oa.sm.service.IPerformParamValidator;
import org.springframework.stereotype.Component;

@Component
@PerformParamValidator(BOOLEAN)
public class BooleanPerformParamValidator implements IPerformParamValidator {

}
