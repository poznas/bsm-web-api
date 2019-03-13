package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value(staticConstructor = "of")
public class TeamId implements ValueObject<String> {

    @NotBlank
    @Size(max = 15)
    private String value;
}
