package com.ronfas.SGBDAPI.inscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.classes.ClasseEntity;
import com.ronfas.SGBDAPI.role.RoleEntity;
import com.ronfas.SGBDAPI.user.UserEntity;
import com.ronfas.SGBDAPI.userTest.UserTestEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "user_cours")
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "User is mandatory.")
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("userCoursList")
    private UserEntity userEntity;

    @NotNull(message = "Class is mandatory.")
    @ManyToOne(targetEntity = ClasseEntity.class)
    @JoinColumn(name = "class_uid", referencedColumnName = "uid", nullable = false)
    @JsonIgnoreProperties("usersList")
    private ClasseEntity classe;

    @NotNull(message = "Role is mandatory.")
    @ManyToOne(targetEntity = RoleEntity.class)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("usersList")
    private RoleEntity roleEntity;

    @OneToMany(mappedBy = "inscriptionEntity")
    @JsonIgnoreProperties({"inscriptionEntity", "id"})
    private List<UserTestEntity> userTestEntityList;

    public InscriptionEntity(Long id, UserEntity userEntity, ClasseEntity classe, RoleEntity roleEntity) {
        this.id = id;
        this.userEntity = userEntity;
        this.classe = classe;
        this.roleEntity = roleEntity;
    }

    public InscriptionEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ClasseEntity getClasse() {
        return classe;
    }

    public void setClasse(ClasseEntity classe) {
        this.classe = classe;
    }

    public RoleEntity getRole() {
        return roleEntity;
    }

    public void setRole(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
