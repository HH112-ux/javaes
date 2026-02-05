package com.jh.inputoutput;

import java.io.*;

/**
 * @author jh
 * @project com.jh.inputoutput
 * @time 2026/2/5
 */
public class TestExternal {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        serialize();
        deserialize();
    }

    public static void serialize() throws IOException {
        Emp emp = new Emp("李白", "男", 22);
        /* emp.setName("李白");
        emp.setGender("男");
        emp.setAge(20);*/
        ObjectOutputStream oos =
                new ObjectOutputStream(new
                        FileOutputStream("d:/aa/emp.yyy"));
        oos.writeObject(emp);
        oos.close();
    }

    public static void deserialize() throws IOException,
            ClassNotFoundException {
        ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream("d:/aa/emp.yyy"));
        Emp emp = (Emp) ois.readObject();
        System.out.println(emp);
    }
}

