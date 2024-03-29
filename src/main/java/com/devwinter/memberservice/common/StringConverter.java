package com.devwinter.memberservice.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class StringConverter {
    public static String localDateTimeToLocalDateTimeString(LocalDateTime localDateTime) {
        return getDateTimeToString(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String localDateTimeToLocalDateString(LocalDateTime localDateTime) {
        return getDateTimeToString(localDateTime, "yyyy년 MM월 dd일");
    }


    private static String getDateTimeToString(LocalDateTime localDateTime, String pattern) {
        if(localDateTime == null) {
            return "";
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}