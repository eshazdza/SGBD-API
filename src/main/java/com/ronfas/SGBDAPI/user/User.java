package com.ronfas.SGBDAPI.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.classes.Classes;
import com.ronfas.SGBDAPI.role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    private String username;
//    private String password;
    @NotBlank(message = "Firstname is mandatory")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "No numbers or special chars allowed.")
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "No numbers or special chars allowed.")
    private String lastname;

//    @ManyToMany
//    @JoinTable(
//            name = "user_classes",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "uid")
//    )
//    private List<Classes> classesList;
//
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonIgnoreProperties("users")
    private Role role;

    public User(Long id, String username, String password, String firstname, String lastname, List<Classes> classesList, Role role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
//        this.classesList = classesList;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
