package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.inscription.Inscription;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uid;

    @NotBlank(message = "ID is mandatory")
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private Date dateBegin;
    private Date dateEnd;

    private boolean currentFlag;

    @OneToMany(mappedBy = "classe")
    private List<Inscription> usersList;

    public Classes(UUID uid, @NotBlank(message = "ID is mandatory") String id, @NotBlank(message = "Name is mandatory") String name, Date dateBegin, Date dateEnd, boolean currentFlag, List<Inscription> usersList) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.currentFlag = currentFlag;
        this.usersList = usersList;
    }


    public Classes() {
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
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

    public List<Inscription> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Inscription> usersList) {
        this.usersList = usersList;
    }
}
