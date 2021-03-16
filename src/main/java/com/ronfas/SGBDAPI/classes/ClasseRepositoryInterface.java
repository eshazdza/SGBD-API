package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ClasseRepositoryInterface extends JpaRepository<ClasseEntity, UUID> {
    Optional<ClasseEntity> findByUid(UUID uuid);

//    @Query(
//            value = "SELECT * FROM user u left join user_cours uc on uc.user_id = u.id left join role r  on r.id = uc.role_id  where r.role_type =0",
//            nativeQuery = true
//    )
//    User findTeacherForClasse(UUID uuid);

    @Query("SELECT u FROM user u left join user_cours uc on uc.userEntity = u left join role r  on r = uc.roleEntity  where r.roleType =0 AND uc.classe.uid = :uuid "
    )
    UserEntity findTeacherForClasse(
            @Param("uuid") UUID uuid
    );
}
