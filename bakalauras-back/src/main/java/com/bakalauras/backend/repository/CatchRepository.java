package com.bakalauras.backend.repository;

import com.bakalauras.backend.models.Catch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatchRepository extends JpaRepository<Catch, Long> {

    Optional<Catch> findByIdentifier(String identifier);
}