package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.FaoArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FaoAreaRepository extends JpaRepository<FaoArea, Long> {

    Optional<FaoArea> findByCode(String code);
}