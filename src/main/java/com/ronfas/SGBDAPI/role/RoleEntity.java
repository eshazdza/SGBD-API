package com.ronfas.SGBDAPI.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.error.InvalidRoleException;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Role type is mandatory")
    private RoleType roleType;

    @Column(unique = true)
    @NotBlank(message = "Description is mandatory")
    private String description;

    @OneToMany(mappedBy = "roleEntity")
    @JsonIgnoreProperties({"id", "roleEntity"})
    private List<InscriptionEntity> userList;

    public RoleEntity(Long id, @NotNull(message = "Role type is mandatory") RoleType roleType, @NotBlank(message = "Description is mandatory") String description, List<InscriptionEntity> userList) {
        this.id = id;
        this.roleType = roleType;
        this.description = description;
        this.userList = userList;
    }

    public RoleEntity() {
    }

    public RoleEntity(String roleType) {
        try {
            this.roleType = RoleType.valueOf(roleType.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidRoleException(RoleEntity.class, "roleType", roleType);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InscriptionEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<InscriptionEntity> usersList) {
        this.userList = usersList;
    }
}
