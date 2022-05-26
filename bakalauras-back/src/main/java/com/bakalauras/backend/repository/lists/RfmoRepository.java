package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.Rfmo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RfmoRepository extends JpaRepository<Rfmo, Long> {
    Optional<Rfmo> findByCode(String code);
}