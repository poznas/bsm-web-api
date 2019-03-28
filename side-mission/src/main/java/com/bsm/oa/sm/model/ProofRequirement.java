package com.bsm.oa.sm.model;

import com.bsm.oa.common.model.DictionaryKey;
import com.bsm.oa.common.model.HttpUrl;
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
public class ProofRequirement {

  /**
   * Proof requirement type
   */
  @NotNull
  private ProofRequirementType type;

  /**
   * Simple proof specification
   */
  @Valid
  private DictionaryKey hint;

  /**
   * example photo url of required scene to record
   */
  private HttpUrl exampleUrl;

}
