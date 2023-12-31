package com.kinetix.surveyengine.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * @author zhangxin
 * @date 2023-06-14 17:54
 */
public class StringUtil {

    public static boolean checkDateFormat(String dateString) {


        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    }

    public static void main(String[] args) {

        final boolean b = checkDateFormat("2011/22/1");

        System.out.println(b);
    }


}
