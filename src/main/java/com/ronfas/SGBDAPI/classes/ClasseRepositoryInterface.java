package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
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

    @Query(" SELECT c FROM classes c left join user_cours uc on uc.classe = c left join user u on u.id = uc.userEntity.id where u.id = :uuid"
    )
    List<ClasseEntity> findUserClasses(
            @Param("uuid") Long uuid
    );

    @Query(value = " select distinct * from classes c " +
            "left join user_cours uc on uc.class_uid = c.uid " +
            "left join `user` u on u.id = uc.user_id " +
            "where c.uid not in ( " +
            "select uc.class_uid from user_cours uc " +
            "where uc.user_id = :uuid" +
            ")group by c.uid;"
            , nativeQuery = true)
    List<ClasseEntity> findUnregisteredForUser(
            @Param("uuid") Long uuid
    );

//    select distinct * from classes c
//    left join user_cours uc on uc.class_uid = c.uid
//    left join `user` u on u.id = uc.user_id
//    where c.uid not in (
//            select uc.class_uid from user_cours uc
//            where uc.user_id = 39
//    )
//            ;

}
