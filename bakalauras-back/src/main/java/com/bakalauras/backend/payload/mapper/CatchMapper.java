package com.bakalauras.backend.payload.mapper;

import com.bakalauras.backend.models.Catch;
import com.bakalauras.backend.models.lists.GearType;
import com.bakalauras.backend.models.lists.Presentation;
import com.bakalauras.backend.models.lists.Preservation;
import com.bakalauras.backend.models.lists.Species;
import com.bakalauras.backend.payload.request.CatchDto;
import com.bakalauras.backend.repository.lists.GearTypeRepository;
import com.bakalauras.backend.repository.lists.PresentationRepository;
import com.bakalauras.backend.repository.lists.PreservationRepository;
import com.bakalauras.backend.repository.lists.SpeciesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CatchMapper {

    private final GearTypeRepository gearTypeRepository;
    private final SpeciesRepository speciesRepository;
    private final PresentationRepository presentationRepository;
    private final PreservationRepository preservationRepository;

    public List<Catch> convertToEntity(List<CatchDto> catchDtos) {
        return catchDtos.stream().map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    private Catch convertToEntity(CatchDto catchDto) {
        return Catch.builder()
                .identifier(catchDto.getIdentifier())
                .gear(getGearType(catchDto.getGearTypeCode()))
                .species(getSpecies(catchDto.getSpecies()))
                .count(catchDto.getCount())
                .weight(catchDto.getWeight())
                .presentation(getPresentation(catchDto.getPresentation()))
                .preservation(getPreservation(catchDto.getPreservation()))
                .build();
    }

    public List<CatchDto> convertToDto(List<Catch> catches) {
        return catches
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public CatchDto convertToDto(Catch catchType) {
        return CatchDto.builder()
                .identifier(catchType.getIdentifier())
                .gearTypeCode(getGearTypeCode(catchType.getGear()))
                .species(getSpeciesCode(catchType.getSpecies()))
                .count(catchType.getCount())
                .weight(catchType.getWeight())
                .presentation(getPresentationCode(catchType.getPresentation()))
                .preservation(getPreservationCode(catchType.getPreservation()))
                .build();
    }

    private Species getSpecies(String code) {
        return speciesRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Species not found %s", code)));
    }

    private GearType getGearType(String code) {
        if (code != null) {
            return gearTypeRepository.findByCode(code)
                    .orElseThrow(() -> new RuntimeException(
                            String.format("Gear type not found %s", code)));
        }
        return null;
    }

    private Preservation getPreservation(String code) {
        return preservationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Preservation not found %s", code)));
    }

    private Presentation getPresentation(String code) {
        return presentationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Presentation not found %s", code)));
    }

    private String getPreservationCode(Preservation preservation) {
        if (preservation != null) {
            return preservation.getCode();
        }
        return null;
    }

    private String getPresentationCode(Presentation presentation) {
        if (presentation != null) {
            return presentation.getCode();
        }
        return null;
    }

    private String getSpeciesCode(Species species) {
        if (species != null) {
            return species.getCode();
        }
        return null;
    }

    private String getGearTypeCode(GearType gear) {
        if (gear != null) {
            return gear.getCode();
        }
        return null;
    }
}
