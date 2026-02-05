package com.jh.inputoutput;

import java.io.*;

/**
 * @author jh
 * @project com.jh.inputoutput
 * @time 2026/2/5
 */
public class TestSeria {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        t1();
        t2();
    }

    private static void t2() throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream("d:/aa/stu.xxx");
        ObjectInputStream ois=new ObjectInputStream(fis);
        Student student =(Student) ois.readObject();
        System.out.println(student);
        ois.close();
    }

    private static void t1() throws IOException {
        Teacher teacher = new Teacher();
        teacher.setTeacherGender("男");
        teacher.setTeacherName("王老师");
        Student student=new Student("李白",22,"男","北京","计算机",teacher);
        FileOutputStream fos=new FileOutputStream("d:/aa/stu.xxx");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(student);
        oos.close();
    }
}
