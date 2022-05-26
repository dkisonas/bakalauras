package com.bakalauras.backend.repository;

import com.bakalauras.backend.models.records.CatchRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatchRecordRepository extends JpaRepository<CatchRecord, Long> {

    Optional<CatchRecord> findByIdentifierAndLogbookIdentifier(String identifier, String logbookIdentifier);
}