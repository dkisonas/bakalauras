package com.bakalauras.backend.repository.lists;

import com.bakalauras.backend.models.lists.Port;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortRepository extends JpaRepository<Port, Long> {
    Optional<Port> findByCode(String code);
}