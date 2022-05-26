package com.bakalauras.backend.service;

import com.bakalauras.backend.models.lists.Nation;
import com.bakalauras.backend.models.Vessel;
import com.bakalauras.backend.models.users.VesselMaster;
import com.bakalauras.backend.payload.mapper.VesselMapper;
import com.bakalauras.backend.payload.request.VesselDto;
import com.bakalauras.backend.payload.request.VesselFilter;
import com.bakalauras.backend.payload.request.VesselUpdateDto;
import com.bakalauras.backend.payload.response.MessageResponse;
import com.bakalauras.backend.repository.VesselMasterRepository;
import com.bakalauras.backend.repository.VesselRepository;
import com.bakalauras.backend.repository.lists.NationRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VesselService {
    private final VesselRepository vesselRepository;
    private final VesselMapper vesselMapper;
    private final VesselMasterRepository vesselMasterRepository;
    private final NationRepository nationRepository;


    public MessageResponse saveVessel(VesselDto vesselDto) {
        Vessel vessel = vesselMapper.convertToEntity(vesselDto);
        Vessel savedVessel = vesselRepository.save(vessel);
        return new MessageResponse(String.format("Vessel {identifier=%s} saved successfully.", savedVessel.getIdentifier()));
    }

    public Vessel getVesselByIdentifier(String identifier) {
        return vesselRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new RuntimeException(String.format("Vessel with identifier {%s} not found", identifier)));
    }

    public List<Vessel> getVessels() {
        return vesselRepository.findAll();
    }

    public MessageResponse updateVessel(@Valid VesselUpdateDto vesselUpdateDto) {
        Vessel vessel = getVesselByIdentifier(vesselUpdateDto.getIdentifier());
        updateName(vesselUpdateDto, vessel);
        updateNation(vesselUpdateDto, vessel);
        updateVesselMaster(vesselUpdateDto, vessel);
        Vessel savedVessel = vesselRepository.save(vessel);
        return new MessageResponse(String.format("Vessel {identifier=%s} updated successfully.", savedVessel.getIdentifier()));
    }

    private void updateVesselMaster(VesselUpdateDto vesselUpdateDto, Vessel vessel) {
        String changedVesselMasterIdentifier = vesselUpdateDto.getVesselMasterIdentifier();
        if (StringUtils.isNotBlank(changedVesselMasterIdentifier)) {
            VesselMaster currentVesselMaster = vessel.getVesselMaster();
            if (currentVesselMaster == null ||
                    !currentVesselMaster.getIdentifier().equalsIgnoreCase(changedVesselMasterIdentifier)) {
                updateVesselMaster(vessel, changedVesselMasterIdentifier);
            }
        }
    }

    private void updateVesselMaster(Vessel vessel, String changedVesselMasterIdentifier) {
        VesselMaster changedVesselMaster = getVesselMaster(changedVesselMasterIdentifier);
        changedVesselMaster.setVessel(vessel);
        vessel.setVesselMaster(changedVesselMaster);
        vesselMasterRepository.save(changedVesselMaster);
    }

    private Nation getNation(String code) {
        return nationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Nation with code %s not found", code)));
    }

    private void updateNation(VesselUpdateDto vesselUpdateDto, Vessel vessel) {
        String changedNationCode = vesselUpdateDto.getNationCode();
        if (!vessel.getNation().getCode().equalsIgnoreCase(changedNationCode)) {
            Nation changedNation = getNation(changedNationCode);
            vessel.setNation(changedNation);
        }
    }

    private void updateName(VesselUpdateDto vesselUpdateDto, Vessel vessel) {
        String changedName = vesselUpdateDto.getName();
        if (!vessel.getName().equalsIgnoreCase(changedName)) {
            vessel.setName(changedName);
        }
    }

    private VesselMaster getVesselMaster(String identifier) {
        return vesselMasterRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new RuntimeException(String.format("Vessel master with identifier {%s} not found", identifier)));
    }

    public List<Vessel> filterVessels(VesselFilter vesselFilter) {
        return vesselRepository.filterVessels(vesselFilter.getVesselIdentifier(), vesselFilter.getNationCode());
    }
}
