package com.bakalauras.backend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GearDto implements Serializable {

    @NotBlank
    private String identifier;

    @NotBlank
    private String gearTypeCode;

}
