package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.inscription.Inscription;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class User extends RepresentationModel<User> {
    private Long id;
    private String firstname;
    private String lastname;
    private boolean isAdmin;
    private List<Inscription> inscriptionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Inscription> getInscriptionList() {
        return inscriptionList;
    }

    public void setInscriptionList(List<Inscription> inscriptionList) {
        this.inscriptionList = inscriptionList;
    }
}
