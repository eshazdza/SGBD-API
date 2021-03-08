package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.inscription.InscriptionModel;
import com.ronfas.SGBDAPI.test.TestModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ClasseModel extends RepresentationModel<ClasseModel> {
    private UUID uuid;
    private String id;
    private String name;
    private Date dateBegin;
    private Date dateEnd;
    private boolean currentFlag;
    private List<InscriptionModel> userList;
    private List<TestModel> testList;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isCurrentFlag() {
        return currentFlag;
    }

    public void setCurrentFlag(boolean currentFlag) {
        this.currentFlag = currentFlag;
    }

    public List<InscriptionModel> getUserList() {
        return userList;
    }

    public void setUserList(List<InscriptionModel> userList) {
        this.userList = userList;
    }

    public List<TestModel> getTestList() {
        return testList;
    }

    public void setTestList(List<TestModel> testList) {
        this.testList = testList;
    }
}
