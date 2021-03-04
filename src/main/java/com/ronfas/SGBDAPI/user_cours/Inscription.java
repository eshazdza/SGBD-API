package com.ronfas.SGBDAPI.user_cours;

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

}
