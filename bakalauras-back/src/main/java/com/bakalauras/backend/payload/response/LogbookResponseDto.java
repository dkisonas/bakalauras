package com.bakalauras.backend.payload.response;

import com.bakalauras.backend.payload.request.CatchRecordDto;
import com.bakalauras.backend.payload.request.DepartureRecordDto;
import com.bakalauras.backend.payload.request.ReturnToPortRecordDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class LogbookResponseDto implements Serializable {
    private final String identifier;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastChangedAt;
    private final String inspectorComment;
    private final DepartureRecordDto departureRecord;
    private final ReturnToPortRecordDto returnToPortRecord;
    private final VesselMasterDto vesselMaster;
    private final List<CatchRecordDto> catchRecords;
}
