package com.ronfas.SGBDAPI.document;

public class BulletinClasse {
    private String id;
    private String name;
    private String teacherFirstName;
    private String teacherLastName;
    private Long pointsAverage;
    private Long studentPoints;
    private String comment;

    public BulletinClasse() {
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

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public Long getPointsAverage() {
        return pointsAverage;
    }

    public void setPointsAverage(Long pointsAverage) {
        this.pointsAverage = pointsAverage;
    }

    public Long getStudentPoints() {
        return studentPoints;
    }

    public void setStudentPoints(Long studentPoints) {
        this.studentPoints = studentPoints;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
