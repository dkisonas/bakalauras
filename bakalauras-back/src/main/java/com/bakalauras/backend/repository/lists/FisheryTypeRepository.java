package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.FisheryType;
import com.bakalauras.backend.models.lists.Port;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FisheryTypeRepository extends JpaRepository<FisheryType, Long> {
    Optional<FisheryType> findByCode(String code);
}