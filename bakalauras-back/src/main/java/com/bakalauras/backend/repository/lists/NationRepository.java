package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NationRepository extends JpaRepository<Nation, Long> {
    Optional<Nation> findByCode(String code);
}