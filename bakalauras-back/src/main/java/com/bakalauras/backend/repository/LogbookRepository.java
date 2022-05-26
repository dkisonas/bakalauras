package com.bakalauras.backend.repository;

import com.bakalauras.backend.models.Logbook;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LogbookRepository extends JpaRepository<Logbook, Long> {
    @EntityGraph(value = "VesselMaster.id", type = EntityGraph.EntityGraphType.FETCH)
    List<Logbook> findAllByVesselMasterId(Long id);

    Optional<Logbook> findByIdentifier(String identifier);

    @Query("SELECT l FROM Logbook l WHERE" +
            " l.identifier like CONCAT('%', :logbookIdentifier, '%') and" +
            " l.vesselMaster.vessel.identifier like CONCAT('%', :vesselIdentifier, '%') and" +
            " l.createdAt BETWEEN :from and :to")
    List<Logbook> filterLogbooks(
            @Param("logbookIdentifier") String logbookIdentifier,
            @Param("vesselIdentifier") String vesselIdentifier,
            @Param("from") String from,
            @Param("to") String to);
}
