package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.ListDTO;
import com.ronfas.SGBDAPI.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    @Query("SELECT t FROM test t left join classes c on t.classe.uid = c.uid where c.uid = :uuid"
    )
    List<TestEntity> findByClasse(
            @Param("uuid") UUID uuid
    );
}
