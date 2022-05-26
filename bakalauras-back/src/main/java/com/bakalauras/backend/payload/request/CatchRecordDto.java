package com.bakalauras.backend.payload.request;

import com.bakalauras.backend.models.CatchOperationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CatchRecordDto implements Serializable {
    @NotBlank
    private final String identifier;
    @NotBlank
    private final String logbookIdentifier;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private final CatchOperationType operationType;
    @NotNull
    @DateTimeFormat
    private final LocalDateTime startDateTime;
    @NotNull
    @DateTimeFormat
    private final LocalDateTime endDateTime;
    @NotBlank
    private final String coordinates;
    @NotBlank
    private final String faoAreaCode;
    @NotBlank
    private final String rfmoCode;
    @NotBlank
    private final String vesselActivityCode;
    @NotNull
    @NumberFormat
    private final Integer operationCount;
    @NotBlank
    private final String fisheryTypeCode;
    @NotBlank
    private final String targetSpeciesGroupCode;
    @Min(value = 1)
    private final List<CatchDto> catches;
}
