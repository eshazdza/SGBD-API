package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.classes.Classes;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
}
