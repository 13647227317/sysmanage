package com.wy.sysmanage.util;


import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换器
 */
public class DateConverter implements Converter<String,LocalDateTime> {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String timeStampFormat = "^\\d+$";

    @Override
    public LocalDateTime convert(String value) {
        if( StringUtils.isEmpty(value) || "null".equalsIgnoreCase(value) ){
            return null;
        }
        if( value.contains("-") ){
            DateTimeFormatter dtf=null;
            if( value.contains(":") ){
               dtf=DateTimeFormatter.ofPattern(dateFormat);

            }else{
                dtf=DateTimeFormatter.ofPattern(shortDateFormat);
            }
            return LocalDateTime.parse(value,dtf);
        }else{
            Instant instant=Instant.ofEpochMilli(Long.parseLong(value));
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
    }
}
