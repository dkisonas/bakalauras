package com.bakalauras.backend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InspectionDto {
    @NotBlank
    private String logbookIdentifier;

    @NotBlank
    private String inspectionComment;
}
