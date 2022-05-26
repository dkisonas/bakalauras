package com.bakalauras.backend.payload.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
public class VesselDto implements Serializable {
    @NotBlank
    private final String name;
    @NotBlank
    private final String nationCode;
}
