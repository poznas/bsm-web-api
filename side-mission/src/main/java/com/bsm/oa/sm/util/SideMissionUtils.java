package com.bsm.oa.sm.util;

import static java.lang.Double.parseDouble;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SideMissionUtils {

  public static Map<String, Double> parsePickerParamEntries(String source) {
    return Stream.of(source.split(","))
      .map(entry -> entry.replaceAll("\\(\\)", ""))
      .collect(toMap(entry -> entry.split(",")[0], entry -> parseDouble(entry.split(",")[1])));
  }

}
