package com.ronfas.SGBDAPI.userTest;

import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.test.Test;
import org.springframework.hateoas.RepresentationModel;

public class UserTest extends RepresentationModel<UserTest> {
    private Long id;
    private Long points;
    private boolean present;
    private Test test;
    private Inscription inscription;

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
