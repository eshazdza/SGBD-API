package com.ronfas.SGBDAPI.document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bulletin {
    private String studentFirstName;
    private String studentLastName;
    private Date dateGen;
    private String schoolYear;
    private List<BulletinClasse> classes;

    public Bulletin() {
        this.classes = new ArrayList<>();
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public Date getDateGen() {
        return dateGen;
    }

    public void setDateGen(Date dateGen) {
        this.dateGen = dateGen;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public List<BulletinClasse> getClasses() {
        return classes;
    }

    public void setClasses(List<BulletinClasse> classes) {
        this.classes = classes;
    }

    public void addClasse(BulletinClasse classe) {
        if (this.classes.isEmpty()) {
            this.classes = new ArrayList<>();
        }
        this.classes.add(classe);
    }
}
