package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.records.ReturnToPortRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReturnToPortRecordRepository extends JpaRepository<ReturnToPortRecord, Long> {
    Optional<ReturnToPortRecord> findByIdentifierAndLogbookIdentifier(String identifier, String logbookIdentifier);
}