package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.VesselActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VesselActivityRepository extends JpaRepository<VesselActivity, Long> {

    Optional<VesselActivity> findByCode(String code);
}