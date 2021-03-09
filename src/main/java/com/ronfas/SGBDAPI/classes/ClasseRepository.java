package com.ronfas.SGBDAPI.classes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClasseRepository extends JpaRepository<ClasseEntity, UUID>  {
    Optional<ClasseEntity> findByUid(UUID uuid);
}
