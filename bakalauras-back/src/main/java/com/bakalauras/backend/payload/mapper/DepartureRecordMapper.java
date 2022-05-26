package com.bakalauras.backend.payload.mapper;

import com.bakalauras.backend.models.lists.*;
import com.bakalauras.backend.models.records.DepartureRecord;
import com.bakalauras.backend.payload.request.DepartureRecordDto;
import com.bakalauras.backend.payload.request.PositionDto;
import com.bakalauras.backend.repository.lists.DepartureReasonRepository;
import com.bakalauras.backend.repository.lists.FisheryTypeRepository;
import com.bakalauras.backend.repository.lists.PortRepository;
import com.bakalauras.backend.repository.lists.TargetSpeciesGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartureRecordMapper {

    private final GearMapper gearMapper;
    private final CatchMapper catchMapper;
    private final PortRepository portRepository;
    private final DepartureReasonRepository departureReasonRepository;
    private final FisheryTypeRepository fisheryTypeRepository;
    private final TargetSpeciesGroupRepository targetSpeciesGroupRepository;

    public DepartureRecord convertToEntity(DepartureRecordDto departureRecordDto) {
        return DepartureRecord.builder()
                .identifier(departureRecordDto.getIdentifier())
                .logbookIdentifier(departureRecordDto.getLogbookIdentifier())
                .dateTime(departureRecordDto.getDateTime())
                .coordinates(departureRecordDto.getPosition().getValue())
                .port(getPort(departureRecordDto.getPosition().getValue()))
                .departureReason(getDepartureReason(departureRecordDto.getDepartureReason()))
                .fisheryType(getFisheryType(departureRecordDto.getFisheryType()))
                .targetSpeciesGroup(getTargetSpeciesGroup(departureRecordDto.getTargetSpeciesGroup()))
                .gearOnBoard(gearMapper.convertToEntity(departureRecordDto.getGearOnBoard()))
                .catchOnBoard(catchMapper.convertToEntity(departureRecordDto.getCatchOnBoard()))
                .build();
    }

    public DepartureRecord convertToEntity(DepartureRecordDto departureRecordDto, String identifier) {
        String posType = departureRecordDto.getPosition().getType();
        String value = departureRecordDto.getPosition().getValue();
        return DepartureRecord.builder()
                .identifier(departureRecordDto.getIdentifier())
                .logbookIdentifier(identifier)
                .dateTime(departureRecordDto.getDateTime())
                .coordinates(posType.equals("POSITION") ? value : null)
                .port(posType.equals("LOCATION") ? getPort(value) : null)
                .departureReason(getDepartureReason(departureRecordDto.getDepartureReason()))
                .fisheryType(getFisheryType(departureRecordDto.getFisheryType()))
                .targetSpeciesGroup(getTargetSpeciesGroup(departureRecordDto.getTargetSpeciesGroup()))
                .gearOnBoard(gearMapper.convertToEntity(departureRecordDto.getGearOnBoard()))
                .catchOnBoard(catchMapper.convertToEntity(departureRecordDto.getCatchOnBoard()))
                .build();
    }

    public DepartureRecordDto convertToDto(DepartureRecord departureRecord) {
        return DepartureRecordDto.builder()
                .identifier(departureRecord.getIdentifier())
                .logbookIdentifier(departureRecord.getLogbookIdentifier())
                .dateTime(departureRecord.getDateTime())
                .position(getPosition(departureRecord.getPort(), departureRecord.getCoordinates()))
                .departureReason(getDepartureRasonCode(departureRecord.getDepartureReason()))
                .fisheryType(getFisheryTypeCode(departureRecord.getFisheryType()))
                .targetSpeciesGroup(getTargetSpeciesGroupCode(departureRecord.getTargetSpeciesGroup()))
                .gearOnBoard(gearMapper.convertToDto(departureRecord.getGearOnBoard()))
                .catchOnBoard(catchMapper.convertToDto(departureRecord.getCatchOnBoard()))
                .build();
    }

    private PositionDto getPosition(Port port, String coordinates) {
        if (port == null) {
            return PositionDto.builder()
                    .type("POSITION")
                    .value(coordinates)
                    .build();
        } else {
            return PositionDto.builder()
                    .type("LOCATION")
                    .value(coordinates)
                    .build();
        }
    }

    private Port getPort(String code) {
        return portRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Port not found %s", code)));
    }

    private TargetSpeciesGroup getTargetSpeciesGroup(String code) {
        return targetSpeciesGroupRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Target species group not found %s", code)));
    }

    private FisheryType getFisheryType(String code) {
        return fisheryTypeRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Fishery type not found %s", code)));
    }

    private DepartureReason getDepartureReason(String code) {
        return departureReasonRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Departure reason not found %s", code)));
    }

    private String getTargetSpeciesGroupCode(TargetSpeciesGroup targetSpeciesGroup) {
        if (targetSpeciesGroup != null) {
            return targetSpeciesGroup.getCode();
        }
        return null;
    }

    private String getFisheryTypeCode(FisheryType fisheryType) {
        if (fisheryType != null) {
            return fisheryType.getCode();
        }
        return null;
    }

    private String getDepartureRasonCode(DepartureReason departureReason) {
        if (departureReason != null) {
            return departureReason.getCode();
        }
        return null;
    }

    private String getPortCode(Port port) {
        if (port != null) {
            return port.getCode();
        }
        return null;
    }
}
