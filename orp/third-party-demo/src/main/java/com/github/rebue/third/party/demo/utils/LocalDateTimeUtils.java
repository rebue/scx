package com.github.rebue.third.party.demo.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class LocalDateTimeUtils {

    /**
     * 默认的日期格式化工具(例如: 2021-09-09)
     */
    public static DateTimeFormatter dtfDefaultDate     = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 默认的日期时间格式化工具(例如: 2021-09-09 09:45:23)
     */
    public static DateTimeFormatter dtfDefaultDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化日期(例如: 2021-09-09)
     */
    public static String formatDate(LocalDate date) {
        return dtfDefaultDate.format(date);
    }

    /**
     * 格式化日期时间(例如: 2021-09-09 09:45:23)
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dtfDefaultDateTime.format(dateTime);
    }

    /**
     * Date转换为LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(final Date date) {
        final Instant instant = date.toInstant();// An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        final ZoneId  zoneId  = ZoneId.systemDefault();// A time-zone ID, such as {@code Europe/Paris}.(时区)
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     */
    public static Date localDateTime2Date(final LocalDateTime localDateTime) {
        final ZoneId        zoneId = ZoneId.systemDefault();
        final ZonedDateTime zdt    = localDateTime.atZone(zoneId);// Combines this date-time with a time-zone to create a ZonedDateTime.
        return Date.from(zdt.toInstant());

    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     */
    public static LocalDateTime getDateTimeOfTimestamp(final long timestamp) {
        final Instant instant = Instant.ofEpochMilli(timestamp);
        final ZoneId  zone    = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 获取LocalDateTime的毫秒数
     */
    public static Long getMillis(final LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 判断字符串是否有效的日期格式
     *
     * @param dateTimeString 要判断的字符串
     * @param pattern        指定的日期格式
     *
     * @return 返回字符串是否有效的日期格式
     */
    public static boolean isValid(final String dateTimeString, final String pattern) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDateTime.parse(dateTimeString, dtf);
        } catch (final DateTimeParseException e1) {
            try {
                LocalDate.parse(dateTimeString, dtf);
            } catch (final DateTimeParseException e2) {
                return false;
            }
        }
        return true;
    }
}
