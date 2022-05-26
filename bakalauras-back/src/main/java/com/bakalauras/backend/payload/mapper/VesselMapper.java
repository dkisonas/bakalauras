package com.bakalauras.backend.payload.mapper;

import com.bakalauras.backend.models.Vessel;


import com.bakalauras.backend.models.lists.Nation;
import com.bakalauras.backend.payload.request.VesselDto;
import com.bakalauras.backend.payload.response.VesselResponseDto;
import com.bakalauras.backend.repository.lists.NationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VesselMapper {
    private final NationRepository nationRepository;
    public List<VesselResponseDto> convertToDto(List<Vessel> vessels) {
        return vessels
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public VesselResponseDto convertToDto(Vessel vessel) {
        return VesselResponseDto.builder()
                .name(vessel.getName())
                .identifier(vessel.getIdentifier())
                .vesselMasterIdentifier(getVesselMasterIdentifier(vessel))
                .nationCode(vessel.getNation().getCode())
                .build();
    }

    public Vessel convertToEntity(VesselDto vesselDto) {
        return Vessel.builder()
                .name(vesselDto.getName())
                .identifier(String.valueOf(UUID.randomUUID()))
                .nation(getNation(vesselDto.getNationCode()))
                .build();
    }

    private Nation getNation(String code) {
        return nationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Nation with code %s not found", code)));
    }

    private String getVesselMasterIdentifier(Vessel vessel) {
        if (vessel.getVesselMaster() != null) {
            return vessel.getVesselMaster().getIdentifier();
        }
        return null;
    }
}
