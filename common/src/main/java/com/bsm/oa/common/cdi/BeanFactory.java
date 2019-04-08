package com.bsm.oa.common.cdi;

import static java.util.Optional.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;
import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = SCOPE_SINGLETON)
public class BeanFactory implements ApplicationContextAware {

  private ApplicationContext context;

  @Override
  public void setApplicationContext(@NonNull ApplicationContext context) {
    this.context = context;
  }

  public <B> B getBean(@NonNull Class<B> beanClass, @NonNull Annotation annotation) {
    return findBeansWithAnnotation(beanClass, annotation.getClass()).get(annotation);
  }

  //.filter(beanClass::isInstance)
  @SuppressWarnings("unchecked")
  private <A extends Annotation, B> Map<A, B> findBeansWithAnnotation(@NonNull Class<B> beanClass,
                                                                      @NonNull Class<A> annClass) {
    Map<A, B> beanMap = new HashMap<>();

    context.getBeansWithAnnotation(annClass).forEach((ann, bean) ->
      of(bean).filter(beanClass::isInstance)
        .map(AopUtils::getTargetClass)
        .map(target -> findMergedAnnotation(target, annClass))
        .ifPresent(annTypeVal -> beanMap.put(annTypeVal, (B) bean)));

    return beanMap;
  }
}
