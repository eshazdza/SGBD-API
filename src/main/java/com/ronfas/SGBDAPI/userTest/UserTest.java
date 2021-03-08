package com.ronfas.SGBDAPI.userTest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.test.Test;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class UserTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long points;
    private boolean present;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"classe", "userTestList"})
    private Test test;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "inscription_id", referencedColumnName = "id")
    private Inscription inscription;

    public UserTest(Long id, Long points, boolean present, Test test, Inscription inscription) {
        this.id = id;
        this.points = points;
        this.present = present;
        this.test = test;
        this.inscription = inscription;
    }

    public UserTest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }
}
