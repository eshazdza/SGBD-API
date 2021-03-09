package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.classes.Classe;
import com.ronfas.SGBDAPI.userTest.UserTest;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

public class Test extends RepresentationModel<Test> {
    private Long id;
    private Date date;
    private Classe classe;
    private List<UserTest> userTestList;

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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<UserTest> getUserTestModelList() {
        return userTestList;
    }

    public void setUserTestModelList(List<UserTest> userTestList) {
        this.userTestList = userTestList;
    }
}
