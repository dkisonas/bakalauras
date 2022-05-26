package com.bakalauras.backend.payload.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class LogbookCreateDto implements Serializable {
    @NotBlank
    private final String identifier;

    @NotNull
    @DateTimeFormat
    private final LocalDateTime createdAt;

    @NumberFormat
    @NotNull
    private final Long vesselMasterId;

    private final DepartureRecordDto departureRecord;
    private final List<CatchRecordDto> catchRecords;
    private final ReturnToPortRecordDto returnToPortRecord;

    @NotNull
    private final boolean sent;
}
