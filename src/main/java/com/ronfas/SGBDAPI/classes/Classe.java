package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.test.Test;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Classe extends RepresentationModel<Classe> {
    private UUID uuid;
    private String id;
    private String name;
    private Date dateBegin;
    private Date dateEnd;
    private boolean currentFlag;
    private List<Inscription> userList = new ArrayList<>();
    private List<Test> testList = new ArrayList<>();

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

    public List<Inscription> getUserList() {
        return userList;
    }

    public void setUserList(List<Inscription> userList) {
        this.userList = userList;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }
}
