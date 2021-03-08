package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.classes.ClasseModel;
import com.ronfas.SGBDAPI.role.RoleModel;
import com.ronfas.SGBDAPI.user.UserModel;
import com.ronfas.SGBDAPI.userTest.UserTestModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class InscriptionModel extends RepresentationModel<InscriptionModel> {
    private Long id;
    private UserModel user;
    private ClasseModel classe;
    private RoleModel role;
    private List<UserTestModel> userTestList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ClasseModel getClasse() {
        return classe;
    }

    public void setClasse(ClasseModel classe) {
        this.classe = classe;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public List<UserTestModel> getUserTestList() {
        return userTestList;
    }

    public void setUserTestList(List<UserTestModel> userTestList) {
        this.userTestList = userTestList;
    }
}
