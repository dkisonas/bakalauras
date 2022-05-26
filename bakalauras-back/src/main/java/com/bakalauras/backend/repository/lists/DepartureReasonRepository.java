package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.DepartureReason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartureReasonRepository extends JpaRepository<DepartureReason, Long> {
    Optional<DepartureReason> findByCode(String code);
}