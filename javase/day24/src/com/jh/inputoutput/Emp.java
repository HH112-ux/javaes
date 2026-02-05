package com.jh.inputoutput;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author jh
 * @project com.jh.inputoutput
 * @time 2026/2/5
 */
public class Emp implements Externalizable {
    private static final long serialVersionUID = -3582785108027188930L;
    private static String name;
    private transient String gender;
    private int age;

    public Emp() {
        System.out.println("无参的构造方法");
    }

    public Emp(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(gender);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        name = in.readUTF();
        gender = in.readUTF();
        age = in.readInt();
    }
}
