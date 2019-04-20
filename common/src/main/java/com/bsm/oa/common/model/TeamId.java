package com.bsm.oa.common.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class TeamId implements ValueObject<String>, Serializable {

    @NotBlank
    @Size(max = 15)
    private String value;
}
