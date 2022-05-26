package com.bakalauras.backend.repository;

import com.bakalauras.backend.models.Gear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GearRepository extends JpaRepository<Gear, Long> {

    Optional<Gear> findByIdentifier(String identifier);
}