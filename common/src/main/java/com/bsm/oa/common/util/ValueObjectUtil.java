package com.bsm.oa.common.util;


import static com.bsm.oa.common.util.ObjectUtils.getOrNull;

import com.bsm.oa.common.model.ValueObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValueObjectUtil {

    public static <T> T getValue(ValueObject<T> object){
        return getOrNull(object, ValueObject::getValue);
    }
}
