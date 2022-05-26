package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.GearType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GearTypeRepository extends JpaRepository<GearType, Long> {
    Optional<GearType> findByCode(String code);
}