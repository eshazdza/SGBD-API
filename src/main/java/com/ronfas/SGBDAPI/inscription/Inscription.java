package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.classes.Classe;
import com.ronfas.SGBDAPI.role.Role;
import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.userTest.UserTest;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class Inscription extends RepresentationModel<Inscription> {
    private Long id;
    private User user;
    private Classe classe;
    private Role role;
    private List<UserTest> userTestList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<UserTest> getUserTestList() {
        return userTestList;
    }

    public void setUserTestList(List<UserTest> userTestList) {
        this.userTestList = userTestList;
    }
}
