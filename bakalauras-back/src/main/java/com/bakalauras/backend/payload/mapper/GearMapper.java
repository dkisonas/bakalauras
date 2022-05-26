package com.bakalauras.backend.payload.mapper;

import com.bakalauras.backend.models.Gear;
import com.bakalauras.backend.models.lists.GearType;
import com.bakalauras.backend.payload.request.GearDto;
import com.bakalauras.backend.repository.GearRepository;
import com.bakalauras.backend.repository.lists.GearTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GearMapper {

    private final GearTypeRepository gearTypeRepository;

    public List<GearDto> convertToDto(List<Gear> gears) {
        return gears
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public GearDto convertToDto(Gear gear) {
        return GearDto.builder()
                .identifier(gear.getIdentifier())
                .gearTypeCode(gear.getGearType().getCode())
                .build();
    }

    public List<Gear> convertToEntity(List<GearDto> gearDtos) {
        return gearDtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public Gear convertToEntity(GearDto gearDto) {
        return Gear.builder()
                .identifier(gearDto.getIdentifier())
                .gearType(getGearType(gearDto.getGearTypeCode()))
                .build();
    }

    private GearType getGearType(String code) {
        return gearTypeRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(
                        String.format("GearType not found %s", code)));
    }
}
