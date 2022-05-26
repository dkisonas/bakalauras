package com.bakalauras.backend.controllers;

import com.bakalauras.backend.payload.request.*;
import com.bakalauras.backend.payload.mapper.LogbookMapper;
import com.bakalauras.backend.service.LogbookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/logbooks")
@AllArgsConstructor
public class LogbooksController {

    private final LogbookService service;
    private final LogbookMapper logbookMapper;


    @PostMapping("/save")
    @PreAuthorize("hasRole('VESSEL_MASTER')")
    public ResponseEntity<?> saveLogbook(@Valid @RequestBody LogbookCreateDto logbookCreateDto) {
        try {
            if (logbookCreateDto.isSent()) {
                return ResponseEntity.ok(service.updateLogbook(logbookCreateDto));
            } else {
                return ResponseEntity.ok(service.saveNewLogbook(logbookCreateDto));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getLogbook(@PathVariable String identifier) {
        try {
            return ResponseEntity.ok(logbookMapper.convertToDto(service.getLogbookByIdentifier(identifier)));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getLogbooks() {
        try {
            return ResponseEntity.ok(logbookMapper.convertToDto(service.getLogbooks()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/inspection")
    public ResponseEntity<?> saveInspectionComment(@Valid @RequestBody InspectionDto inspectionDto) {
        try {
            return ResponseEntity.ok(service.saveInspectionComment(inspectionDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{identifier}")
    public ResponseEntity<?> deleteLogbook(@PathVariable String identifier) {
        try {
            return ResponseEntity.ok(service.deleteLogbook(identifier));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterLogbooks(@RequestBody LogbookFilter logbookFilter) {
        logbookFilter.setEmptyFilters();
        return ResponseEntity.ok(logbookMapper.convertToDto(service.filterLogbooks(logbookFilter)));
    }

    @PatchMapping("/update-departure")
    public ResponseEntity<?> updateDepartureRecord(@Valid @RequestBody DepartureRecordDto departureRecordDto) {
        try {
            return ResponseEntity.ok(service.updateDepartureRecord(departureRecordDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/update-catch")
    public ResponseEntity<?> updateCatchRecord(@Valid @RequestBody CatchRecordDto catchRecordDto) {
        try {
            return ResponseEntity.ok(service.updateCatchRecord(catchRecordDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/update-return-to-port")
    public ResponseEntity<?> updateReturnToPortRecord(@Valid @RequestBody ReturnToPortRecordDto returnToPortRecordDto) {
        try {
            return ResponseEntity.ok(service.updateReturnToPortRecord(returnToPortRecordDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

}
