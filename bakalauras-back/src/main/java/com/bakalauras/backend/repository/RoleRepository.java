package com.bakalauras.backend.repository;


import com.bakalauras.backend.models.users.ERole;
import com.bakalauras.backend.models.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
