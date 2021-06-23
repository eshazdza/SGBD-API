package com.ronfas.SGBDAPI.userTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTestRepository extends JpaRepository<UserTestEntity, Long> {

    @Query("  SELECT ut from user_test ut " +
            " left join user_cours uc on ut.inscriptionEntity.id = uc.id " +
            " left join user u on uc.userEntity.id = u.id " +
            "where uc.roleEntity.id = 3 and u.id = :uuid"
    )
    List<UserTestEntity> findUserTests(
            @Param("uuid") Long uuid
    );

}
