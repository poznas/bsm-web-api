package com.bsm.oa.sm.model;

import com.bsm.oa.common.model.DictionaryName;
import com.bsm.oa.common.model.HttpUrl;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SideMissionType {

  /**
   * Side mission type identifier
   */
  @Valid
  @NotNull
  private SideMissionTypeID typeId;

  /**
   * Perform parameters
   */
  @Valid
  @NotEmpty
  private List<PerformParam> performParams;

  /**
   * Equation for the number of points
   */
  @Valid
  @NotNull
  private Equation equation;

  /**
   * link to document describing mission rules
   */
  @Valid
  private HttpUrl descriptionDocumentUrl;

  /**
   * photo/video requirements that must be attached when submitting that mission
   */
  @Valid
  @NotEmpty
  private List<ProofRequirement> proofRequirements;

  /**
   * dictionary containing mission type specific literals
   */
  @Valid
  private DictionaryName dictionaryName;

}
