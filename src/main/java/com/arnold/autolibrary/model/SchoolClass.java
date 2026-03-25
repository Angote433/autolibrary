package com.arnold.autolibrary.model;

import java.time.Year;

public class SchoolClass {
    private int classID;
    private String className;  //Grade 7
    private int gradeLevel;
    private Year academicYear;

    public SchoolClass(String className,int gradeLevel,Year academicYear){
        this.className = className;
        this.gradeLevel = gradeLevel;
        this.academicYear = academicYear;
    }



}
