package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.inscription.Inscription;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class Role extends RepresentationModel<Role> {
    private Long id;
    private RoleType roleType;
    private String description;
    private List<Inscription> userList;

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

    public List<Inscription> getUserList() {
        return userList;
    }

    public void setUserList(List<Inscription> userList) {
        this.userList = userList;
    }
}
