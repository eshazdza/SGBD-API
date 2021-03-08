package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.inscription.InscriptionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class RoleModel extends RepresentationModel<RoleModel> {
    private Long id;
    private RoleType roleType;
    private String description;
    private List<InscriptionModel> userList;

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

    public List<InscriptionModel> getUserList() {
        return userList;
    }

    public void setUserList(List<InscriptionModel> userList) {
        this.userList = userList;
    }
}
