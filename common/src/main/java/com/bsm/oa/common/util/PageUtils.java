package com.bsm.oa.common.util;

import com.bsm.oa.common.model.PageableFilter;
import java.util.List;
import java.util.function.Function;
import javax.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@UtilityClass
public class PageUtils {

  public static <T, F extends PageableFilter> Page<T> retrievePage(@NotNull F filter,
                                                                   @NotNull Pageable pageable,
                                                                   @NotNull Function<F, List<T>> getContent,
                                                                   @NotNull Function<F, Long> getTotalCount
  ) {
    filter.setPageSize(pageable.getPageSize());
    filter.setOffset(pageable.getOffset());

    return new PageImpl<>(getContent.apply(filter), pageable, getTotalCount.apply(filter));
  }
}
