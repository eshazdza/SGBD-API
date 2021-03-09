package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.inscription.InscriptionModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

public class RoleModel extends RepresentationModel<RoleModel> {
    private Long id;
    private RoleType roleType;
    private String description;
    private CollectionModel<InscriptionModel> userList;

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

    public CollectionModel<InscriptionModel> getUserList() {
        return userList;
    }

    public void setUserList(CollectionModel<InscriptionModel> userList) {
        this.userList = userList;
    }
}
