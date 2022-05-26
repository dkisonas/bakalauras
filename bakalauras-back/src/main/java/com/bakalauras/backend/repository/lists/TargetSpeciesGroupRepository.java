package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.Port;
import com.bakalauras.backend.models.lists.TargetSpeciesGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetSpeciesGroupRepository extends JpaRepository<TargetSpeciesGroup, Long> {
    Optional<TargetSpeciesGroup> findByCode(String code);
}