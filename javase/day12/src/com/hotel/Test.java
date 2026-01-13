package com.hotel;

import com.hotel.exception.FugitiveException;
import com.hotel.model.Fugitive;
import com.hotel.model.Guest;

/**
 * @author jh
 * @project com.hotel
 * @time 2026/1/13
 */
public class Test {
    public static void main(String[] args) {
        Fugitive f1 = new Fugitive("110101199901011234", "张麻子");
        Fugitive f2 = new Fugitive("440202198802025678", "李二狗");
        Fugitive[] fugitives = {f1, f2};
        Hotel hotel = new Hotel(fugitives);
        hotel.guests = new Guest[10];
        Guest g1 = new Guest("王老五", 25, "330303199503037890");
        Guest g2 = new Guest("赵老六", 30, "110101199901011234");
        Guest g3 = new Guest("孙小七", 28, "22020219930505123");
        hotel.checkInHotel(g2);
        hotel.checkInHotel(g3);
        try {
            hotel.checkIdCard();
            System.out.println("所有客人身份核验通过，无异常！");
        } catch (IllegalArgumentException e) {
            System.out.println("核验失败：" + e.getMessage());
        } catch (FugitiveException e) {
            System.out.println("核验失败：" + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("程序异常：空指针，无客人入住或数组未初始化");
        }
    }
}

