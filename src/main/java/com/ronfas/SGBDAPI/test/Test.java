package com.ronfas.SGBDAPI.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.classes.Classes;
import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.userTest.UserTest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @NotNull(message = "Classe is mandatory")
    @ManyToOne
    @JoinColumn(name = "class_uid", referencedColumnName = "uid")
    private Classes classe;

    @OneToMany(mappedBy = "test")
    @JsonIgnoreProperties({"test", "id"})
    private List<UserTest> userTestList;

    public Test(Long id, Date date, @NotNull(message = "Classe is mandatory") Classes classe, List<UserTest> userTestList) {
        this.id = id;
        this.date = date;
        this.classe = classe;
        this.userTestList = userTestList;
    }

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        this.classe = classe;
    }

    public List<UserTest> getUserTestList() {
        return userTestList;
    }

    public void setUserTestList(List<UserTest> userTestList) {
        this.userTestList = userTestList;
    }
}
