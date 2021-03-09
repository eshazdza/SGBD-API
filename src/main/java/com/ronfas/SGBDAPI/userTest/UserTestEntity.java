package com.ronfas.SGBDAPI.userTest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;
import com.ronfas.SGBDAPI.test.TestEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "user_test")
public class UserTestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long points;
    private boolean present;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"classe", "userTestList"})
    private TestEntity testEntity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "inscription_id", referencedColumnName = "id")
    private InscriptionEntity inscriptionEntity;

    public UserTestEntity(Long id, Long points, boolean present, TestEntity testEntity, InscriptionEntity inscriptionEntity) {
        this.id = id;
        this.points = points;
        this.present = present;
        this.testEntity = testEntity;
        this.inscriptionEntity = inscriptionEntity;
    }

    public UserTestEntity() {
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

    public TestEntity getTest() {
        return testEntity;
    }

    public void setTest(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    public InscriptionEntity getInscription() {
        return inscriptionEntity;
    }

    public void setInscription(InscriptionEntity inscriptionEntity) {
        this.inscriptionEntity = inscriptionEntity;
    }
}
