package com.bakalauras.backend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class VesselUpdateDto implements Serializable {
    @NotBlank
    private final String identifier;

    private final String name;

    private final String vesselMasterIdentifier;

    private final String nationCode;
}
