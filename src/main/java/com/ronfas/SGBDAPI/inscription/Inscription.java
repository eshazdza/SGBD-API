package com.ronfas.SGBDAPI.inscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.classes.Classes;
import com.ronfas.SGBDAPI.role.Role;
import com.ronfas.SGBDAPI.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "user_cours")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "User is mandatory.")
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("userCoursList")
    private User user;

    @NotNull(message = "Class is mandatory.")
    @ManyToOne(targetEntity = Classes.class)
    @JoinColumn(name = "class_uid", referencedColumnName = "uid", nullable = false)
    @JsonIgnoreProperties("usersList")
    private Classes classe;

    @NotNull(message = "Role is mandatory.")
    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("usersList")
    private Role role;

    public Inscription(Long id, User user, Classes classe, Role role) {
        this.id = id;
        this.user = user;
        this.classe = classe;
        this.role = role;
    }

    public Inscription() {

    }

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

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        this.classe = classe;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
