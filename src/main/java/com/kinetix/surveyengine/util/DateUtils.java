package com.kinetix.surveyengine.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author melo
 * @date 2023-06-16 11:15
 */
public class DateUtils {


    public static Date StringToDate(String dateString) {

        if (StringUtils.isEmpty(dateString)) {
            return null;
        }

        // 创建 SimpleDateFormat 对象，指定日期时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        // 将字符串解析为 Date 对象
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static void main(String[] args) {
      /*  Date date = DateUtils.StringToDate("2010-01-01T00:00:00");
        System.out.println(date);*/

        final BigDecimal bigDecimal = new BigDecimal("");

        System.out.println(bigDecimal);

    }


}
