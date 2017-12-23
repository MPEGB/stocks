package com.mpegb.investments.util;

import java.time.format.DateTimeFormatter;

/**
 * Created by bhushan on 23/12/2017.
 */
public final class CustomDateFormat {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static DateTimeFormatter getFormat(){
       return DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    private CustomDateFormat(){}
}
