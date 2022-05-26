package com.bakalauras.backend.repository;

import com.bakalauras.backend.models.users.VesselMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VesselMasterRepository extends JpaRepository<VesselMaster, Long> {

    Optional<VesselMaster> findByIdentifier(String identifier);
}