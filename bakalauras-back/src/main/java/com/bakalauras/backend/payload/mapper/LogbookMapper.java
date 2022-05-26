package com.bakalauras.backend.payload.mapper;

import com.bakalauras.backend.exception.VesselMasterNotFound;
import com.bakalauras.backend.models.Logbook;
import com.bakalauras.backend.models.users.VesselMaster;
import com.bakalauras.backend.payload.request.LogbookCreateDto;
import com.bakalauras.backend.payload.response.LogbookResponseDto;
import com.bakalauras.backend.repository.VesselMasterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class LogbookMapper {

    private final DepartureRecordMapper departureRecordMapper;
    private final CatchRecordMapper catchRecordMapper;
    private final ReturnToPortRecordMapper returnToPortRecordMapper;
    private final VesselMasterRepository vesselMasterRepository;

    public List<LogbookResponseDto> convertToDto(List<Logbook> logbooks) {
        return logbooks
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LogbookResponseDto convertToDto(Logbook logbook) {
        return LogbookResponseDto.builder()
                .identifier(logbook.getIdentifier())
                .createdAt(logbook.getCreatedAt())
                .lastChangedAt(logbook.getLastChangedAt())
                .inspectorComment(logbook.getInspectorComment())
                .departureRecord(departureRecordMapper.convertToDto(logbook.getDepartureRecord()))
                .catchRecords(catchRecordMapper.convertToDto(logbook.getCatchRecords()))
                .returnToPortRecord(returnToPortRecordMapper.convertToDto(logbook.getReturnToPortRecord()))
                .build();
    }

    public Logbook convertToEntity(LogbookCreateDto logbookCreateDto) {
        String identifier = logbookCreateDto.getIdentifier();
        return Logbook.builder()
                .identifier(identifier)
                .createdAt(logbookCreateDto.getCreatedAt())
                .vesselMaster(getVesselMaster(logbookCreateDto.getVesselMasterId()))
                .departureRecord(departureRecordMapper.convertToEntity(logbookCreateDto.getDepartureRecord(), identifier))
                .catchRecords(catchRecordMapper.convertToEntity(logbookCreateDto.getCatchRecords()))
                .returnToPortRecord(returnToPortRecordMapper.convertToEntity(logbookCreateDto.getReturnToPortRecord()))
                .build();
    }

    private VesselMaster getVesselMaster(Long id) {
        return vesselMasterRepository.getById(id);
    }

}
