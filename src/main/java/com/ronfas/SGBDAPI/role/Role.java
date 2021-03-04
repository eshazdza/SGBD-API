package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.error.InvalidRoleException;
import com.ronfas.SGBDAPI.user_cours.Inscription;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Role type is mandatory")
    private RoleType roleType;

    @Column(unique = true)
    @NotBlank(message = "Description is mandatory")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<Inscription> usersList;

    public Role(Long id, @NotNull(message = "Role type is mandatory") RoleType roleType, @NotBlank(message = "Description is mandatory") String description, List<Inscription> usersList) {
        this.id = id;
        this.roleType = roleType;
        this.description = description;
        this.usersList = usersList;
    }

    public Role() {
    }

    public Role(String roleType) {
        try {
            this.roleType = RoleType.valueOf(roleType.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidRoleException(Role.class, "roleType", roleType);
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

    public List<Inscription> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Inscription> usersList) {
        this.usersList = usersList;
    }
}
