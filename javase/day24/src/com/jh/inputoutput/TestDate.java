package com.jh.inputoutput;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author jh
 * @project com.jh.inputoutput
 * @time 2026/2/5
 */
public class TestDate {
    public static void main(String[] args) throws IOException {
        /* DataOutputStream dos=new DataOutputStream(new
        FileOutputStream("d:/aa/x.xx"));
        Student student=new Student();
        student.setName("李白");
        student.setAge(20);
        dos.writeUTF(student.getName());
        dos.writeInt(student.getAge());
        dos.close();*/
        DataInputStream dis=new DataInputStream(new
                FileInputStream("d:/aa/x.xx"));
        String name = dis.readUTF();
        int age=dis.readInt();
        System.out.println(name+"\t"+age);
        dis.close();
    }
}
