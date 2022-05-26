package com.bakalauras.backend.payload.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class VesselResponseDto implements Serializable {
    private final String identifier;
    private final String name;
    private final String vesselMasterIdentifier;
    private final String nationCode;
}
