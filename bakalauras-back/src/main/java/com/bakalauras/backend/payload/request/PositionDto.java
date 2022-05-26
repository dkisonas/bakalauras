package com.bakalauras.backend.payload.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class PositionDto {
    @NotBlank
    private String type;
    @NotBlank
    private String value;
}
