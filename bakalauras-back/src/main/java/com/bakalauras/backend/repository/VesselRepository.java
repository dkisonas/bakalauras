package com.bakalauras.backend.repository;

import com.bakalauras.backend.models.Logbook;
import com.bakalauras.backend.models.Vessel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VesselRepository extends JpaRepository<Vessel, Long> {
    @EntityGraph(value = "VesselMaster.id", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Vessel> findByVesselMasterId(Long vesselMasterId);

    Optional<Vessel> findByIdentifier(String identifier);

    @Query("SELECT v FROM Vessel v WHERE" +
            " v.identifier like CONCAT('%', :vesselIdentifier, '%') or" +
            " v.nation.code like CONCAT('%', :nationCode, '%')")
    List<Vessel> filterVessels(
            @Param("vesselIdentifier") String vesselIdentifier,
            @Param("nationCode") String nationCode);

}
