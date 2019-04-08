package com.bsm.oa.sm.model;

import com.bsm.oa.common.model.DictionaryKey;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PerformParam {

  /**
   * Symbol representing parameter in mission points equation
   */
  @Valid
  @NotNull
  private PerformParamSymbol symbol;

  /**
   * perform param type, TODO: dict name comment
   */
  @NotNull
  private PerformParamType type;

  /**
   * If param type is LIMITED_INTEGER, available limit is specified
   */
  private Integer availableLimit;

  /**
   * If param type is STRING_PICKER, available keys are specified
   */
  private Set<String> availableKeys;

  /**
   * Simple hint for judge
   */
  @Valid
  private DictionaryKey hint;

  /**
   * Specifies whether a judge or professor should assign a value to this param
   */
  @NotNull
  private RaterType toRateBy;

  /**
   * raw string kept in db, represents available values
   * + if LIMITED_INTEGER string matches -> ^\d+$
   * + if STRING_PICKER string matches -> ^(\([^\(,]+,[\d\.]+\),)*\([^\(,]+,[\d\.]+\)$
   */
  private String availableValuesSource;

}
