package com.jh.inputoutput.demo;

/**
 * @author jh
 * @project com.jh.inputoutput.demo
 * @time 2026/2/5
 */

import com.jh.inputoutput.entity.Student;
import com.jh.inputoutput.entity.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class UserSystem {
    private static final String USER_DIR = "users/";
    private static final String PROP_FILE = "info.properties";
    private static final String LOG_FILE = "operation.log";

    public static void main(String[] args) {
        File userDir = new File(USER_DIR);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----用户系统-----");
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 退出");
            System.out.print("请选择操作：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    if (login(scanner)) {
                        processData();
                    }
                    break;
                case 3:
                    System.out.println("系统已退出");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效选项，请重新选择");
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        File userFile = new File(USER_DIR + username + ".ser");
        if (userFile.exists()) {
            System.out.println("该用户名已存在，注册失败");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
            User user = new User(username, password);
            oos.writeObject(user);
            System.out.println("注册成功");
        } catch (IOException e) {
            System.out.println("注册失败：" + e.getMessage());
        }
    }

    private static boolean login(Scanner scanner) {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        File userFile = new File(USER_DIR + username + ".ser");
        if (!userFile.exists()) {
            System.out.println("用户不存在");
            return false;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            User user = (User) ois.readObject();
            if (user.getPassword().equals(password)) {
                System.out.println("登录成功");
                return true;
            } else {
                System.out.println("密码错误，登录失败");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("登录失败：" + e.getMessage());
        }
        return false;
    }

    private static void processData() {
        Properties prop = new Properties();
        try (InputStream is = new FileInputStream(PROP_FILE)) {
            prop.load(is);
        } catch (IOException e) {
            System.out.println("读取配置文件失败：" + e.getMessage());
            return;
        }

        Student student = new Student(
                prop.getProperty("student.name"),
                prop.getProperty("student.gender"),
                Integer.parseInt(prop.getProperty("student.age"))
        );
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject(student);
        } catch (IOException e) {
            System.out.println("学生信息序列化失败：" + e.getMessage());
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("teacher.dat"))) {
            dos.writeUTF(prop.getProperty("teacher.name"));
            dos.writeInt(Integer.parseInt(prop.getProperty("teacher.age")));
        } catch (IOException e) {
            System.out.println("教师信息写入失败：" + e.getMessage());
        }

        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write("操作人：" + prop.getProperty("operator") + "\n");
            fw.write("操作时间：" + new Date() + "\n");
            fw.write("学生文件大小：" + Files.size(Paths.get("student.dat")) + "字节\n");
            fw.write("教师文件大小：" + Files.size(Paths.get("teacher.dat")) + "字节\n");
            fw.write("---------------------------------------\n");
        } catch (IOException e) {
            System.out.println("日志记录失败：" + e.getMessage());
        }

        System.out.println("数据处理完成，日志已记录");
    }
}

