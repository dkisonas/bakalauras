package com.bakalauras.backend.payload.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class CatchDto implements Serializable {
    @NotBlank
    private final String identifier;

    @NotBlank
    private final String gearTypeCode;

    @NotBlank
    private final String species;

    @NumberFormat
    private final Integer count;

    @NotNull
    @NumberFormat
    private final Double weight;

    @NotBlank
    private final String presentation;

    @NotBlank
    private final String preservation;


}
