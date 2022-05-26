package com.bakalauras.backend.payload.response;

import com.bakalauras.backend.payload.request.VesselDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class VesselMasterDto implements Serializable {
    @NotBlank
    @Size(max = 20)
    private final String username;
    @NotBlank
    @Size(max = 120)
    private final String name;
    @NotBlank
    @Size(max = 120)
    private final String lastName;
    private final String identifier;
    private final VesselDto vessel;
}
