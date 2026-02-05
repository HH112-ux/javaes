package com.jh.inputoutput;

import java.io.Serializable;

/**
 * @author jh
 * @project com.jh.inputoutput
 * @time 2026/2/5
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    private String teacherName;
    private String teacherGender;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(String teacherGender) {
        this.teacherGender = teacherGender;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherName='" + teacherName + '\'' +
                ", teacherGender='" + teacherGender + '\'' +
                '}';
    }
}
