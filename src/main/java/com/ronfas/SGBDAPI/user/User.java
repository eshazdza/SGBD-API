package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.inscription.Inscription;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Firstname is mandatory")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "No numbers or special chars allowed.")
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "No numbers or special chars allowed.")
    private String lastname;

    private boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private List<Inscription> userCoursList;

    public User(Long id, @NotBlank(message = "Firstname is mandatory") @Pattern(regexp = "^[a-zA-Z]*$", message = "No numbers or special chars allowed.") String firstname, @NotBlank(message = "Lastname is mandatory") @Pattern(regexp = "^[a-zA-Z]*$", message = "No numbers or special chars allowed.") String lastname, boolean isAdmin, List<Inscription> userCoursList) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
        this.userCoursList = userCoursList;
    }

    public User() {

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

    public List<Inscription> getUserCoursList() {
        return userCoursList;
    }

    public void setUserCoursList(List<Inscription> userCoursList) {
        this.userCoursList = userCoursList;
    }
}
