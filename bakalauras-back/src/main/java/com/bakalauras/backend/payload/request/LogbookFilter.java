package com.bakalauras.backend.payload.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class LogbookFilter {
    private String logbookIdentifier;
    private String vesselIdentifier;
    @DateTimeFormat
    private String from;
    @DateTimeFormat
    private String to;

    public void setEmptyFilters() {
        if (vesselIdentifier == null) {
            vesselIdentifier = "*";
        }

        if (logbookIdentifier == null) {
            logbookIdentifier = "*";
        }
        if (from == null) {
            logbookIdentifier = "*";
        }
        if (to == null) {
            logbookIdentifier = "*";
        }
    }
}
