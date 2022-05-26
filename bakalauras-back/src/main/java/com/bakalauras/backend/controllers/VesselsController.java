package com.bakalauras.backend.controllers;

import com.bakalauras.backend.payload.request.VesselDto;
import com.bakalauras.backend.payload.request.VesselFilter;
import com.bakalauras.backend.payload.request.VesselUpdateDto;
import com.bakalauras.backend.payload.mapper.VesselMapper;
import com.bakalauras.backend.service.VesselService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/vessels")
@AllArgsConstructor
public class VesselsController {

    private final VesselService service;
    private final VesselMapper vesselMapper;

    @GetMapping
    public ResponseEntity<?> getVessels() {
        try {
            return ResponseEntity.ok(vesselMapper.convertToDto(service.getVessels()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterVessels(@RequestBody VesselFilter vesselFilter) {
        vesselFilter.setEmptyFilters();
        return ResponseEntity.ok(vesselMapper.convertToDto(service.filterVessels(vesselFilter)));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateVessel(@Valid @RequestBody VesselUpdateDto vesselUpdateDto) {
        try {
            return ResponseEntity.ok(service.updateVessel(vesselUpdateDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getVessel(@PathVariable String identifier) {
        try {
            return ResponseEntity.ok(vesselMapper.convertToDto(service.getVesselByIdentifier(identifier)));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveVessel(@Valid @RequestBody VesselDto vesselDto) {
        try {
            return ResponseEntity.ok(service.saveVessel(vesselDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}
