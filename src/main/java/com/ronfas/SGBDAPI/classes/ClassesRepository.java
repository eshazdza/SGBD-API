package com.ronfas.SGBDAPI.classes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClassesRepository extends JpaRepository<Classes, UUID>  {
    Optional<Classes> findByUid(UUID uuid);
}
