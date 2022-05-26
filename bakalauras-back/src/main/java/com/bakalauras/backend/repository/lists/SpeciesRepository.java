package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.Port;
import com.bakalauras.backend.models.lists.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    Optional<Species> findByCode(String code);
}