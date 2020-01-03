package com.wy.sysmanage.config;

import com.wy.sysmanage.util.DateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * 日期转换配置
 * @author wangyong
 */
@Configuration
public class DateConfig {
    @Bean
    public Converter<String, LocalDateTime> dateConverter(){
        return new DateConverter();
    }
}
