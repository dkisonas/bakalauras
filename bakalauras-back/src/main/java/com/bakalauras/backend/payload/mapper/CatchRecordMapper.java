package com.bakalauras.backend.payload.mapper;

import com.bakalauras.backend.models.lists.*;
import com.bakalauras.backend.models.records.CatchRecord;
import com.bakalauras.backend.payload.request.CatchRecordDto;
import com.bakalauras.backend.repository.lists.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CatchRecordMapper {

    private final CatchMapper catchMapper;
    private final FisheryTypeRepository fisheryTypeRepository;
    private final TargetSpeciesGroupRepository targetSpeciesGroupRepository;
    private final VesselActivityRepository vesselActivityRepository;
    private final FaoAreaRepository faoAreaRepository;
    private final RfmoRepository rfmoRepository;

    public List<CatchRecordDto> convertToDto(List<CatchRecord> catchRecords) {
        return catchRecords
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CatchRecordDto convertToDto(CatchRecord catchRecord) {
        return CatchRecordDto.builder()
                .identifier(catchRecord.getIdentifier())
                .logbookIdentifier(catchRecord.getLogbookIdentifier())
                .operationType(catchRecord.getOperationType())
                .startDateTime(catchRecord.getStartDateTime())
                .endDateTime(catchRecord.getEndDateTime())
                .coordinates(catchRecord.getCoordinates())
                .faoAreaCode(getFaoAreaCode(catchRecord.getFaoArea()))
                .rfmoCode(getRfmoCode(catchRecord.getRfmo()))
                .targetSpeciesGroupCode(getTargetSpeciesGroupCode(catchRecord.getTargetSpeciesGroup()))
                .catches(catchMapper.convertToDto(catchRecord.getCatches()))
                .build();
    }

    public List<CatchRecord> convertToEntity(List<CatchRecordDto> catchRecords) {
        return catchRecords.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public CatchRecord convertToEntity(CatchRecordDto catchRecordDto) {
        return CatchRecord.builder()
                .identifier(catchRecordDto.getIdentifier())
                .logbookIdentifier(catchRecordDto.getLogbookIdentifier())
                .operationType(catchRecordDto.getOperationType())
                .startDateTime(catchRecordDto.getStartDateTime())
                .endDateTime(catchRecordDto.getEndDateTime())
                .coordinates(catchRecordDto.getCoordinates())
                .faoArea(getFaoArea(catchRecordDto.getFaoAreaCode()))
                .rfmo(getRfmo(catchRecordDto.getRfmoCode()))
                .vesselActivity(getVesselActivity(catchRecordDto.getVesselActivityCode()))
                .operationCount(catchRecordDto.getOperationCount())
                .fisheryType(getFisheryType(catchRecordDto.getFisheryTypeCode()))
                .targetSpeciesGroup(getTargetSpeciesGroup(catchRecordDto.getTargetSpeciesGroupCode()))
                .catches(catchMapper.convertToEntity(catchRecordDto.getCatches()))
                .build();
    }

    private FisheryType getFisheryType(String code) {
        return fisheryTypeRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Fishery type not found %s", code)));
    }

    private TargetSpeciesGroup getTargetSpeciesGroup(String code) {
        return targetSpeciesGroupRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Target species group not found %s", code)));
    }

    private VesselActivity getVesselActivity(String code) {
        return vesselActivityRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Vessel activity not found %s", code)));
    }

    private Rfmo getRfmo(String code) {
        return rfmoRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("RFMO not found %s", code)));
    }

    private FaoArea getFaoArea(String code) {
        return faoAreaRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("FAO Area not found %s", code)));
    }

    private String getTargetSpeciesGroupCode(TargetSpeciesGroup targetSpeciesGroup) {
        if (targetSpeciesGroup != null) {
            return targetSpeciesGroup.getCode();
        }
        return null;
    }

    private String getRfmoCode(Rfmo rfmo) {
        if (rfmo != null) {
            return rfmo.getCode();
        }
        return null;
    }

    private String getFaoAreaCode(FaoArea faoArea) {
        if (faoArea != null) {
            return faoArea.getCode();
        }
        return null;
    }
}
