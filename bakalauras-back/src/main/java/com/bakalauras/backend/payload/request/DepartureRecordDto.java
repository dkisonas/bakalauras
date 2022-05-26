package com.bakalauras.backend.payload.request;

import com.bakalauras.backend.models.lists.GearType;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DepartureRecordDto implements Serializable {
    @NotBlank
    private final String identifier;
    private final String logbookIdentifier;
    @NotNull
    @DateTimeFormat
    private final LocalDateTime dateTime;
    @NotNull
    private final PositionDto position;
    @NotBlank
    private final String departureReason;
    @NotBlank
    private final String fisheryType;
    @NotBlank
    private final String targetSpeciesGroup;
    @Min(value = 1)
    private final List<GearDto> gearOnBoard;
    private final List<CatchDto> catchOnBoard;
}
