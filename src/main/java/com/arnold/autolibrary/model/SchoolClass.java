package com.arnold.autolibrary.model;

import jakarta.persistence.*;

import java.time.Year;
@Entity
@Table(name = "schoolclass")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classID;

    @Column(nullable = false,length = 50)
    private String className;

    @Column(nullable = false)
    private int gradeLevel;

    @Column(nullable= false)
    private int academicYear;


    public SchoolClass(){}
    public SchoolClass(String className,int gradeLevel,int academicYear){
        this.className = className;
        this.gradeLevel=gradeLevel;
        this.academicYear = academicYear;
    }
    public int getClassID(){return classID;}
    public void setClassID(int classID){this.classID = classID;}

    public String getClassName(){return className;}
    public void setClassName(String className){this.className = className;}

    public int getGradeLevel(){return gradeLevel;}
    public void setGradeLevel(int gradeLevel){this.gradeLevel=gradeLevel;}

    public int getAcademicYear(){return academicYear;}
    public void setAcademicYear(int academicYear){this.academicYear=academicYear;}
}
