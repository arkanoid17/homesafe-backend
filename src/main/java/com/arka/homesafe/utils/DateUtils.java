package com.arka.homesafe.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date getDateAfter(int days){
        LocalDate futureDate = LocalDate.now().plusDays(days);

        // Convert LocalDate to Date (if needed)
        return Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

}
