package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.classes.Classes;
import com.ronfas.SGBDAPI.role.Role;
import com.ronfas.SGBDAPI.user.User;

import javax.persistence.*;

@Entity(name = "user_cours")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Classes.class)
    @JoinColumn(name = "class_uid", referencedColumnName = "uid")
    private Classes classe;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public Inscription(Long id, User user, Classes classe, Role role) {
        this.id = id;
        this.user = user;
        this.classe = classe;
        this.role = role;
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
