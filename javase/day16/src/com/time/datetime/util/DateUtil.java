package com.time.datetime.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author jh
 * @project com.time.datetime.util
 * @time 2026/1/19
 */
public class DateUtil {
    private enum DateType {
        DAYS, MONTHS, YEARS
    }

    private static DateType type = null;

    public static long calculateBetween(LocalDate start, LocalDate end, String typ) {
        if(typ.equals("day")||typ.equals("DAY")) {
            type = DateType.DAYS;
        }else if(typ.equals("month")||typ.equals("MONTH")) {
            type = DateType.MONTHS;
        }else if(typ.equals("year")||typ.equals("YEAR")) {
            type = DateType.YEARS;
        }
        switch (type) {
            case DAYS:
                return ChronoUnit.DAYS.between(start, end);
            case MONTHS:
                return ChronoUnit.MONTHS.between(start, end);
            case YEARS:
                return ChronoUnit.YEARS.between(start, end);
            default:
                System.out.println("错误日期类型");
        }
        return 0;
    }

}

