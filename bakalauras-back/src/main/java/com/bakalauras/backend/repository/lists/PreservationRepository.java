package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.Presentation;
import com.bakalauras.backend.models.lists.Preservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreservationRepository extends JpaRepository<Preservation, Long> {
    Optional<Preservation> findByCode(String code);
}