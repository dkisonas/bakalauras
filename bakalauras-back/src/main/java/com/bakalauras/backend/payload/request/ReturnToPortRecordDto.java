package com.bakalauras.backend.payload.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class ReturnToPortRecordDto implements Serializable {
    @NotBlank
    private final String identifier;
    @NotBlank
    private final String logbookIdentifier;
    @NotNull
    @DateTimeFormat
    private final LocalDateTime dateTime;
    @NotBlank
    private final String coordinates;
    @NotBlank
    private final String portCode;
    @NotBlank
    private final String returnReasonCode;
}
