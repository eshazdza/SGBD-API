package com.ronfas.SGBDAPI.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM user u WHERE u.email = ?1 ")
    UserEntity findByEmail(String email);
}
