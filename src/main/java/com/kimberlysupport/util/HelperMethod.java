package com.kimberlysupport.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperMethod {
    private static Pattern imageFileExtnPtrn = Pattern.compile("(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$");


    public static LocalDate convertStrToDate(String dateStr, String dateFormat) {
        LocalDate date = null;
        if (dateStr != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
            date = LocalDate.parse(dateStr, dateTimeFormatter);
        }
        return date;
    }

    public static String convertDateToStr(LocalDate date, String dateFormat) {
        String dateStr = null;
        if (date != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
            dateStr = date.format(dateTimeFormatter);
        }
        return dateStr;
    }
    public static String convertDateTimeToStr(LocalDateTime datetime, String dateFormat) {
        String dateStr = null;
        if (datetime != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
            dateStr = datetime.format(dateTimeFormatter);
        }
        return dateStr;
    }
    public static String convertTimeToStr(LocalTime time, String dateFormat) {
        String timeStr = null;
        if (time != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
            timeStr = time.format(dateTimeFormatter);
        }
        return timeStr;
    }
    public static boolean isValidImage(String filename) {
        Matcher mtch = imageFileExtnPtrn.matcher(filename);
        if (mtch.matches()) {
            return true;
        }
        return false;
    }

}
