package com.bsm.oa.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value(staticConstructor = "of")
public class Username implements ValueObject<String> {

    public static final int MAX_SIZE = 30;

    @NotBlank
    @Size(max = MAX_SIZE)
    private String value;
}
