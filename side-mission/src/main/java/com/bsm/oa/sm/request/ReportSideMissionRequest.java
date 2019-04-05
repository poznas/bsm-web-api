package com.bsm.oa.sm.request;

import com.bsm.oa.common.model.AwsS3Url;
import com.bsm.oa.common.model.SideMissionTypeID;
import com.bsm.oa.common.model.UserId;
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
  private List<AwsS3Url> proofUrls;

}
