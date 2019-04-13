package com.bsm.oa.common.util;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CollectionUtils {

  public static <T, E> List<T> mapList(Collection<E> collection, Function<E, T> function) {
    return ofNullable(collection).orElse(emptyList()).stream().map(function).collect(toList());
  }

  public static <T, E> Set<T> mapSet(Collection<E> collection, Function<E, T> function) {
    return ofNullable(collection).orElse(emptyList()).stream().map(function).collect(toSet());
  }

  public static <T> Map<Boolean, List<T>> partition(Collection<T> collection,
                                                    Predicate<T> predicate) {
    return streamOfNullable(collection).collect(partitioningBy(predicate));
  }

  public static <T> Stream<T> streamOfNullable(Collection<T> collection) {
    return ofNullable(collection).orElse(emptySet()).stream();
  }
}
