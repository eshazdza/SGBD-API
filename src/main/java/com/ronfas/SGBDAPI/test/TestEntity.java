package com.ronfas.SGBDAPI.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.classes.ClasseEntity;
import com.ronfas.SGBDAPI.userTest.UserTestEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @NotNull(message = "Classe is mandatory")
    @ManyToOne
    @JoinColumn(name = "class_uid", referencedColumnName = "uid")
    @JsonIgnoreProperties({"testsList"})
    private ClasseEntity classe;

    @OneToMany(mappedBy = "testEntity")
    @JsonIgnoreProperties({"testEntity", "id"})
    private List<UserTestEntity> userTestEntityList;

    public TestEntity(Long id, Date date, @NotNull(message = "Classe is mandatory") ClasseEntity classe, List<UserTestEntity> userTestEntityList) {
        this.id = id;
        this.date = date;
        this.classe = classe;
        this.userTestEntityList = userTestEntityList;
    }

    public TestEntity() {
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

    public ClasseEntity getClasse() {
        return classe;
    }

    public void setClasse(ClasseEntity classe) {
        this.classe = classe;
    }

    public List<UserTestEntity> getUserTestList() {
        return userTestEntityList;
    }

    public void setUserTestList(List<UserTestEntity> userTestEntityList) {
        this.userTestEntityList = userTestEntityList;
    }
}
