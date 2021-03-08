package com.ronfas.SGBDAPI.userTest;

import com.ronfas.SGBDAPI.inscription.InscriptionModel;
import com.ronfas.SGBDAPI.test.TestModel;
import org.springframework.hateoas.RepresentationModel;

public class UserTestModel extends RepresentationModel<UserTestModel> {
    private Long id;
    private Long points;
    private boolean present;
    private TestModel test;
    private InscriptionModel inscription;

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

    public TestModel getTest() {
        return test;
    }

    public void setTest(TestModel test) {
        this.test = test;
    }

    public InscriptionModel getInscription() {
        return inscription;
    }

    public void setInscription(InscriptionModel inscription) {
        this.inscription = inscription;
    }
}
