package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.classes.Classes;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "class_uid", referencedColumnName = "uid")
    private Classes classe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}