package com.bakalauras.backend.service;

import com.bakalauras.backend.models.Catch;
import com.bakalauras.backend.models.Gear;
import com.bakalauras.backend.models.Logbook;
import com.bakalauras.backend.models.lists.GearType;
import com.bakalauras.backend.models.records.CatchRecord;
import com.bakalauras.backend.models.records.DepartureRecord;
import com.bakalauras.backend.models.records.ReturnToPortRecord;
import com.bakalauras.backend.models.users.VesselMaster;
import com.bakalauras.backend.payload.mapper.CatchRecordMapper;
import com.bakalauras.backend.payload.mapper.DepartureRecordMapper;
import com.bakalauras.backend.payload.mapper.ReturnToPortRecordMapper;
import com.bakalauras.backend.payload.request.*;
import com.bakalauras.backend.payload.response.MessageResponse;
import com.bakalauras.backend.payload.mapper.LogbookMapper;
import com.bakalauras.backend.repository.*;
import com.bakalauras.backend.repository.lists.GearTypeRepository;
import com.bakalauras.backend.repository.lists.ReturnToPortRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LogbookService {

    private final LogbookMapper logbookMapper;
    private final DepartureRecordMapper departureRecordMapper;
    private final CatchRecordMapper catchRecordMapper;
    private final ReturnToPortRecordMapper returnToPortRecordMapper;
    private final LogbookRepository logbookRepository;
    private final DepartureRecordRepository departureRecordRepository;
    private final CatchRecordRepository catchRecordRepository;
    private final ReturnToPortRecordRepository returnToPortRecordRepository;
    private final VesselMasterRepository vesselMasterRepository;
    private final CatchRepository catchRepository;
    private final GearRepository gearRepository;

    public MessageResponse saveNewLogbook(LogbookCreateDto logbookCreateDto) {
        Logbook logbook = logbookMapper.convertToEntity(logbookCreateDto);
        DepartureRecord departureRecord = logbook.getDepartureRecord();
        setGearsAndCatches(departureRecord);
        logbook.setDepartureRecord(departureRecord);
        departureRecordRepository.save(departureRecord);
        VesselMaster vesselMaster = logbook.getVesselMaster();
        vesselMaster.getLogbooks().add(logbook);
        vesselMasterRepository.save(vesselMaster);
        logbook.setLastChangedAt(LocalDateTime.now());
        Logbook savedLogbook = logbookRepository.save(logbook);
        return new MessageResponse(String.format("Logbook {identifier=%s} saved successfully.", savedLogbook.getIdentifier()));
    }

    private void setGearsAndCatches(DepartureRecord departureRecord) {
        List<Gear> gears = saveGears(departureRecord.getGearOnBoard());
        List<Catch> catches = saveCatches(departureRecord.getCatchOnBoard());
        departureRecord.setCatchOnBoard(catches);
        departureRecord.setGearOnBoard(gears);
    }

    private List<Gear> saveGears(List<Gear> gearOnBoard) {
        List<Gear> someGears = new ArrayList<>();
        for (Gear gear : gearOnBoard) {
            someGears.add(gearRepository.save(gear));
        }
        return someGears;
    }

    private List<Catch> saveCatches(List<Catch> catchOnBoard) {
        List<Catch> someCatches = new ArrayList<>();
        for (Catch aCatch : catchOnBoard) {
            someCatches.add(catchRepository.save(aCatch));
        }
        return someCatches;
    }

    public Logbook getLogbookByIdentifier(String identifier) {
        return logbookRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new RuntimeException(String.format("Logbook with identifier {%s} not found", identifier)));
    }

    public List<Logbook> getLogbooks() {
        return logbookRepository.findAll();
    }

    public MessageResponse saveInspectionComment(InspectionDto inspectionDto) {
        Logbook logbook = getLogbookByIdentifier(inspectionDto.getLogbookIdentifier());
        logbook.setInspectorComment(inspectionDto.getInspectionComment());
        logbookRepository.save(logbook);
        return new MessageResponse(String.format("Inspection comment successfully saved in logbook {identifier=%s}", logbook.getIdentifier()));
    }

    public MessageResponse updateDepartureRecord(DepartureRecordDto departureRecordDto) {
        DepartureRecord departureRecord = getDepartureRecord(departureRecordDto.getIdentifier(), departureRecordDto.getLogbookIdentifier());
        DepartureRecord updatedDepartureRecord = departureRecordMapper.convertToEntity(departureRecordDto);
        updatedDepartureRecord.setId(departureRecord.getId());
        DepartureRecord savedDepartureRecord = departureRecordRepository.save(updatedDepartureRecord);

        return new MessageResponse(String.format("Departure record {identifier=%s} {logbook_identifier=%s} saved successfully.",
                savedDepartureRecord.getIdentifier(), savedDepartureRecord.getIdentifier()));

    }

    public MessageResponse updateCatchRecord(CatchRecordDto catchRecordDto) {
        CatchRecord catchRecord = getCatchRecord(catchRecordDto.getIdentifier(), catchRecordDto.getLogbookIdentifier());
        CatchRecord updatedCatchRecord = catchRecordMapper.convertToEntity(catchRecordDto);
        updatedCatchRecord.setId(catchRecord.getId());
        CatchRecord savedCatchRecord = catchRecordRepository.save(updatedCatchRecord);
        return new MessageResponse(String.format("Catch record {identifier=%s} {logbook_identifier=%s} saved successfully.",
                savedCatchRecord.getIdentifier(), savedCatchRecord.getLogbookIdentifier()));

    }

    public MessageResponse updateReturnToPortRecord(ReturnToPortRecordDto returnToPortRecordDto) {
        ReturnToPortRecord returnToPortRecord = getReturnToPortRecord(
                returnToPortRecordDto.getIdentifier(), returnToPortRecordDto.getLogbookIdentifier());
        ReturnToPortRecord updatedReturnToPortRecord = returnToPortRecordMapper.convertToEntity(returnToPortRecordDto);
        updatedReturnToPortRecord.setId(returnToPortRecord.getId());
        ReturnToPortRecord savedReturnToPortRecord = returnToPortRecordRepository.save(updatedReturnToPortRecord);
        return new MessageResponse(String.format("Return to port record {identifier=%s} {logbook_identifier=%s} saved successfully.",
                savedReturnToPortRecord.getIdentifier(), savedReturnToPortRecord.getLogbookIdentifier()));
    }

    private ReturnToPortRecord getReturnToPortRecord(String identifier, String logbookIdentifier) {
        return returnToPortRecordRepository.findByIdentifierAndLogbookIdentifier(identifier, logbookIdentifier)
                .orElseThrow(() -> new RuntimeException(String.format("Return to port record {identifier=%s} {logbook_identifier=%s} not found", identifier, logbookIdentifier)));
    }

    public List<Logbook> filterLogbooks(LogbookFilter logbookFilter) {
        return logbookRepository.filterLogbooks(logbookFilter.getLogbookIdentifier(),
                logbookFilter.getVesselIdentifier(),
                logbookFilter.getFrom(),
                logbookFilter.getTo());
    }

    private DepartureRecord getDepartureRecord(String identifier, String logbookIdentifier) {
        return departureRecordRepository.findByIdentifierAndLogbookIdentifier(identifier, logbookIdentifier)
                .orElseThrow(() -> new RuntimeException(String.format("Departure record {identifier=%s} {logbook_identifier=%s} not found", identifier, logbookIdentifier)));
    }

    private CatchRecord getCatchRecord(String identifier, String logbookIdentifier) {
        return catchRecordRepository.findByIdentifierAndLogbookIdentifier(identifier, logbookIdentifier)
                .orElseThrow(() -> new RuntimeException(String.format("Catch record {identifier=%s} {logbook_identifier=%s} not found", identifier, logbookIdentifier)));
    }

    public MessageResponse deleteLogbook(String identifier) {
        Logbook logbook = getLogbookByIdentifier(identifier);
        logbookRepository.delete(logbook);
        return new MessageResponse("Logbook successfully deleted.");
    }

    public MessageResponse updateLogbook(LogbookCreateDto logbookCreateDto) {
        return new MessageResponse("Not implemented.");
    }
}
