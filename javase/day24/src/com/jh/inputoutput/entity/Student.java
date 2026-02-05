package com.jh.inputoutput.entity;

/**
 * @author jh
 * @project com.jh.inputoutput.entity
 * @time 2026/2/5
 */
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Student implements Externalizable {
    private String name;
    private String gender;
    private int age;

    public Student() {}

    public Student(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(gender);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = (String) in.readObject();
        this.gender = (String) in.readObject();
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}

