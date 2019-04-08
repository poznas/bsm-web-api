package com.bsm.oa.sm.annotation;

import com.bsm.oa.sm.model.PerformParamType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.AnnotationLiteral;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformParamValidator {

  PerformParamType value();


  @RequiredArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  final class Literal extends AnnotationLiteral<PerformParamValidator> implements
    PerformParamValidator {

    private final PerformParamType type;

    @Override
    public PerformParamType value() {
      return type;
    }
  }
}
