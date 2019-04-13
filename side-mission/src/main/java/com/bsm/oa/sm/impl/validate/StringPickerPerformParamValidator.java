package com.bsm.oa.sm.impl.validate;

import static com.bsm.oa.sm.model.PerformParamType.STRING_PICKER;
import static java.lang.Double.parseDouble;
import static java.util.stream.Collectors.toMap;

import com.bsm.oa.sm.annotation.PerformParamValidator;
import com.bsm.oa.sm.model.PerformParam;
import com.bsm.oa.sm.service.IPerformParamValidator;
import java.util.Map;
import java.util.stream.Stream;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@PerformParamValidator(STRING_PICKER)
public class StringPickerPerformParamValidator implements IPerformParamValidator {

  @Override
  public void validateRateValue(@Valid @NotNull PerformParam param, @NotNull Double value) {
    var entries = parseAvailableValueSource(param, this::parsePickerParamEntries);

    var matchedEntry = entries.values().stream().filter(value::equals).findFirst();

    if(matchedEntry.isEmpty()) {
      raiseInvalidRateValueException(param.getSymbol(), value, entries);
    }
  }

  private Map<String, Double> parsePickerParamEntries(String source) {
    return Stream.of(source.split("\\),\\("))
      .map(entry -> entry.replaceAll("\\)", ""))
      .map(entry -> entry.replaceAll("\\(", ""))
      .map(entry -> entry.split(","))
      .collect(toMap(entry -> entry[0], entry -> parseDouble(entry[1])));
  }
}
