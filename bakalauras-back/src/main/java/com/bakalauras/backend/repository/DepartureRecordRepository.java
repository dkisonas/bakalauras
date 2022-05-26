package com.bakalauras.backend.repository;

import com.bakalauras.backend.models.records.DepartureRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartureRecordRepository extends JpaRepository<DepartureRecord, Long> {
    Optional<DepartureRecord> findByIdentifierAndLogbookIdentifier(String identifier, String logbookIdentifier);
}