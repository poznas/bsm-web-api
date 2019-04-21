package com.bsm.oa.dict;

import static com.bsm.oa.common.util.ValueObjectUtil.getValue;
import static com.bsm.oa.dict.DictionaryController.CONTEXT;

import com.bsm.dict.api.model.DictionaryItem;
import com.bsm.oa.common.model.DictionaryName;
import com.bsm.oa.common.model.IsoLangCode;
import com.bsm.oa.common.service.connector.DictionaryConnector;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(CONTEXT)
@RequiredArgsConstructor
public class DictionaryController {

  static final String CONTEXT = "/dictionary";
  private static final String DICTIONARY_PATH = "/{dictName}";

  private final DictionaryConnector dictionaryConnector;

  @GetMapping(DICTIONARY_PATH)
  List<DictionaryItem> getDictionary(
    @Valid @NotNull @PathVariable("dictName") DictionaryName dictName,
    @Valid @RequestParam(value = "lang", required = false) IsoLangCode lang) {
    return dictionaryConnector.getDictionary(getValue(dictName), getValue(lang));
  }

}
