package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.ReturnReason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReturnReasonRepository extends JpaRepository<ReturnReason, Long> {
    Optional<ReturnReason> findByCode(String code);
}