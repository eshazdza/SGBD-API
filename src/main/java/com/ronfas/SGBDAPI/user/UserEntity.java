package com.ronfas.SGBDAPI.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Firstname is mandatory")
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @Column(unique = true)
    @NotBlank(message = "email required")
    private String email;

    @NotBlank(message = "password required")
    private String password;

    @Transient
    private String confirmPassword;

    private boolean isAdmin;

    @OneToMany(mappedBy = "userEntity")
    @JsonIgnoreProperties({"id", "userEntity"})
    private List<InscriptionEntity> inscriptionList;

    public UserEntity(Long id, @NotBlank(message = "Firstname is mandatory") String firstname, @NotBlank(message = "Lastname is mandatory") String lastname, boolean isAdmin, List<InscriptionEntity> inscriptionList) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
        this.inscriptionList = inscriptionList;
    }

    public UserEntity() {

    }

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

    public List<InscriptionEntity> getInscriptionList() {
        return inscriptionList;
    }

    public void setInscriptionList(List<InscriptionEntity> userCoursList) {
        this.inscriptionList = userCoursList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
