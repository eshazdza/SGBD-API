package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.classes.ClasseModel;
import com.ronfas.SGBDAPI.userTest.UserTestModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

public class TestModel extends RepresentationModel<TestModel> {
    private Long id;
    private Date date;
    private ClasseModel classe;
    private List<UserTestModel> userTestModelList;

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

    public ClasseModel getClasse() {
        return classe;
    }

    public void setClasse(ClasseModel classe) {
        this.classe = classe;
    }

    public List<UserTestModel> getUserTestModelList() {
        return userTestModelList;
    }

    public void setUserTestModelList(List<UserTestModel> userTestModelList) {
        this.userTestModelList = userTestModelList;
    }
}
