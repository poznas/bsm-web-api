package com.bsm.oa.common.service.connector;

import com.bsm.dict.api.model.DictionaryItem;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/** @noinspection SpringCacheAnnotationsOnInterfaceInspection*/
@CacheConfig(cacheNames = {"dictionary"})
@FeignClient(value = "dictionary-client", configuration = FeignConfig.class)
public interface DictionaryConnector {

  @Cacheable
  @GetMapping("/dictionary/{dictionaryKey}")
  List<DictionaryItem> getDictionary(
    @NotBlank @PathVariable("dictionaryKey") String dictionaryKey,
    @Pattern(regexp = "[A-Z]{2}") @RequestParam(value = "lang", required = false) String lang);
}
