package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.error.InvalidRoleException;
import com.ronfas.SGBDAPI.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private RoleType roleType;
    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role(Long id, RoleType roleType, String description) {
        this.id = id;
        this.roleType = roleType;
        this.description = description;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
