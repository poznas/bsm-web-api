package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value(staticConstructor = "of")
public class UserId implements ValueObject<String> {

    @NotBlank
    @Size(max = 21)
    private String value;
}
