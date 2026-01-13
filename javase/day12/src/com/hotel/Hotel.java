package com.hotel;

import com.hotel.exception.FugitiveException;
import com.hotel.model.Fugitive;
import com.hotel.model.Guest;

/**
 * @author jh
 * @project com.hotel
 * @time 2026/1/13
 */
public class Hotel {
    private Fugitive[] fugitives;
    protected Guest[] guests;
private int numGuest=0;
    public Hotel(Fugitive[] fugitives) {
        this.fugitives = fugitives;
    }
    public void checkInHotel(Guest guest) {
        guests[numGuest] = guest;
        numGuest++;
    }

    public void checkIdCard() throws FugitiveException {
        for (Guest g : guests) {
            String id = g.getIdcard();
            if (id.length() != 18) {
                throw new IllegalArgumentException("身份证号码错误，身份证号码为18位");
            }
            for (Fugitive fugitive : fugitives) {
                if (id.equals(fugitive.getIdCard())) {
                    throw new FugitiveException("他就是逃犯");
                }
            }

        }
    }
}
