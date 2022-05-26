package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    Optional<Presentation> findByCode(String code);
}