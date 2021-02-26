package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;

    private String id;
    private String name;
    private Date dateBegin;
    private Date dateEnd;
    private boolean currentFlag;
//
//    @ManyToMany(mappedBy = "classesList")
//    private List<User> userList;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
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
}
