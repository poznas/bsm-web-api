package com.bsm.oa.sm.request;

import com.bsm.oa.common.model.SideMissionTypeID;
import com.bsm.oa.common.model.UserId;
import com.bsm.oa.sm.model.ProofMediaLink;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
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
public class ReportSideMissionRequest {

  /**
   * side mission type identifier
   */
  @Valid
  @NotNull
  private SideMissionTypeID missionTypeId;

  /**
   * user who performed mission
   */
  @Valid
  @NotNull
  private UserId performingUserId;

  /**
   * AWS S3 bucket proof photo / video locations
   */
  @Valid
  private List<ProofMediaLink> proofMediaLinks;

  /**
   * reporting user ID, filled by server before inserting into DB
   */
  @JsonIgnore
  private UserId reportingUserId;

}
