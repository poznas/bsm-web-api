package com.bsm.oa.common.util;

import static java.util.Optional.ofNullable;

import java.util.function.Function;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectUtils {

    public static <T, E> E getOrNull(T source, Function<T, E> mapper) {
        return getOrDefault(source, mapper, null);
    }

    public static <T, E> E getOrDefault(T source, Function<T, E> mapper, E other) {
        return ofNullable(source).map(mapper).orElse(other);
    }
}
