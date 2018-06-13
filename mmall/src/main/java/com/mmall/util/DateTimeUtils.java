package com.mmall.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DateTimeUtils {
    public static String DEFAULT_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final long MILLIS_OF_DAY = 86400000L;
    private static final long MILLIS_OF_HOUR = 3600000L;
    private static final long MILLIS_OF_MINUTE = 60000L;
    private static final long MILLIS_OF_SECOND = 1000L;

    public DateTimeUtils() {
    }

    public void setDefaultFormat(String defaultFormat) {
        DEFAULT_FORMAT = defaultFormat;
    }

    private static Calendar getCalendar(Date date, DateTimeUtils.TimeMode timeMode) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (DateTimeUtils.TimeMode.BEGIN_OF_DAY == timeMode) {
            calendar.set(10, 0);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
        } else if (DateTimeUtils.TimeMode.END_OF_DAY == timeMode) {
            calendar.set(10, 23);
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
        }

        return calendar;
    }

    public static Date newByNowTime(int year, int month, int day) {
        Calendar calendar = getCalendar(now(), DateTimeUtils.TimeMode.NOW_OF_DAY);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static Date newBeginDate(int year, int month, int day) {
        Calendar calendar = getCalendar(now(), DateTimeUtils.TimeMode.BEGIN_OF_DAY);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static Date newEndDate(int year, int month, int day) {
        Calendar calendar = getCalendar(now(), DateTimeUtils.TimeMode.END_OF_DAY);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static Date newByNowDate(int hour, int minute, int second) {
        Calendar calendar = getCalendar(now(), DateTimeUtils.TimeMode.NOW_OF_DAY);
        calendar.set(11, hour);
        calendar.set(12, minute);
        calendar.set(13, second);
        return calendar.getTime();
    }

    public static Date newByNowTime(int hour, int minute, int second, int millisecond) {
        Calendar calendar = getCalendar(now(), DateTimeUtils.TimeMode.NOW_OF_DAY);
        calendar.set(11, hour);
        calendar.set(12, minute);
        calendar.set(13, second);
        calendar.set(14, millisecond);
        return calendar.getTime();
    }

    public static Date newDateTime(int year, int month, int day, int hour, int minute, int second) {
        return newDateTime(year, month, day, hour, minute, second, 0);
    }

    public static Date newDateTime(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar calendar = getCalendar(now(), DateTimeUtils.TimeMode.NOW_OF_DAY);
        calendar.set(year, month - 1, day, hour, minute, second);
        if (millisecond != 0) {
            calendar.set(14, millisecond);
        }

        return calendar.getTime();
    }

    public static int[] toArray(Date date) {
        int[] values = new int[8];
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        values[0] = calendar.get(1);
        values[1] = calendar.get(2) + 1;
        values[2] = calendar.get(5);
        values[3] = calendar.get(7) - 1;
        values[4] = calendar.get(11);
        values[5] = calendar.get(12);
        values[6] = calendar.get(13);
        values[7] = calendar.get(14);
        return values;
    }

    public static int[] toArray() {
        return toArray(now());
    }

    public static int getYear(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(1);
    }

    public static int getNowYear() {
        return getYear(now());
    }

    public static int getMonth(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(2) + 1;
    }

    public static int getNowMonth() {
        return getMonth(now());
    }

    public static int getDay(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(5);
    }

    public static int getNowDay() {
        return getDay(now());
    }

    public static int getDayOfOneYear(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(6);
    }

    public static int getNowDayOfOneYear() {
        return getDayOfOneYear(now());
    }

    public static int getWeek(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(7) - 1;
    }

    public static int getNowWeek() {
        return getWeek(now());
    }

    public static int getWeekOfOneYear(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(3);
    }

    public static int getNowWeekOfOneYear() {
        return getWeekOfOneYear(now());
    }

    public static int getWeekOfOneMonth(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(4);
    }

    public static int getNowWeekOfOneMonth() {
        return getWeekOfOneMonth(now());
    }

    public static int getHour(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(11);
    }

    public static int getNowHour() {
        return getHour(now());
    }

    public static int getMinute(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(12);
    }

    public static int getNowMinute() {
        return getMinute(now());
    }

    public static int getSecond(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(13);
    }

    public static int getNowSecond() {
        return getSecond(now());
    }

    public static int getMillisecond(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.NOW_OF_DAY);
        return calendar.get(14);
    }

    public static int getNowMillisecond() {
        return getMillisecond(now());
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static boolean isLeapYear(Date date) {
        int year = getYear(date);
        return isLeapYear(year);
    }

    public static boolean isLeapOfNowYear() {
        return isLeapYear(now());
    }

    public int getDaysOfMonth(int year, int month) {
        int days = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 2:
                days = isLeapYear(year) ? 29 : 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
        }

        return days;
    }

    public int getDaysOfMonth(Date date) {
        int[] array = toArray(date);
        return this.getDaysOfMonth(array[0], array[1]);
    }

    public int getDaysOfNowMonth() {
        return this.getDaysOfMonth(now());
    }

    public int getDaysOfYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    public int getDaysOfYear(Date date) {
        return isLeapYear(date) ? 366 : 365;
    }

    public int getDaysOfNowYear() {
        return isLeapOfNowYear() ? 366 : 365;
    }

    public static Date now() {
        return newDate(System.currentTimeMillis());
    }

    public static long nowTimestamp() {
        return System.currentTimeMillis();
    }

    public static long timestamp(Date date) {
        return date.getTime();
    }

    public static Date newDate(long timestamp) {
        return new Date(timestamp);
    }

    public static Date newDateTime(Date date) {
        return new Date(date.getTime());
    }

    public static Date beginOfToday() {
        return beginOfDay(now());
    }

    public static Date endOfToday() {
        return endOfDay(now());
    }

    public static Date beginOfDay(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
        return calendar.getTime();
    }

    public static Date endOfDay(Date date) {
        Calendar calendar = getCalendar(date, DateTimeUtils.TimeMode.END_OF_DAY);
        return calendar.getTime();
    }

    public static Date addDays(int days) {
        return addDays(now(), days, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date addDays(Date date, int days) {
        return addDays(date, days, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date addDays(Date date, int days, DateTimeUtils.TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (days != 0) {
            calendar.add(5, days);
        }

        return calendar.getTime();
    }

    public static Date minusDays(int days) {
        return addDays(now(), -days, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date minusDays(Date date, int days) {
        return addDays(date, -days, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date minusDays(Date date, int days, DateTimeUtils.TimeMode timeMode) {
        return addDays(date, -days, timeMode);
    }

    public static Date addMonths(Date date, int months) {
        return addMonths(date, months, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date addMonths(Date date, int months, DateTimeUtils.TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (months != 0) {
            calendar.add(2, months);
        }

        return calendar.getTime();
    }

    public static Date minusMonths(Date date, int months) {
        return addMonths(date, -months, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date minusMonths(Date date, int months, DateTimeUtils.TimeMode timeMode) {
        return addMonths(date, -months, timeMode);
    }

    public static Date addYears(Date date, int years) {
        return addYears(date, years, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date addYears(Date date, int years, DateTimeUtils.TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (years != 0) {
            calendar.add(1, years);
        }

        return calendar.getTime();
    }

    public static Date minusYears(Date date, int years) {
        return addYears(date, -years, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date minusYears(Date date, int years, DateTimeUtils.TimeMode timeMode) {
        return addYears(date, -years, timeMode);
    }

    public static Date addHours(Date date, int hours) {
        return addHours(date, hours, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date addHours(Date date, int hours, DateTimeUtils.TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (hours != 0) {
            calendar.add(11, hours);
        }

        return calendar.getTime();
    }

    public static Date minusHours(Date date, int hours) {
        return addHours(date, -hours, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date minusHours(Date date, int hours, DateTimeUtils.TimeMode timeMode) {
        return addHours(date, -hours, timeMode);
    }

    public static Date addMinutes(Date date, int minutes) {
        return addMinutes(date, minutes, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date addMinutes(Date date, int minutes, DateTimeUtils.TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (minutes != 0) {
            calendar.add(12, minutes);
        }

        return calendar.getTime();
    }

    public static Date minusMinutes(Date date, int minutes) {
        return addMinutes(date, -minutes, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date minusMinutes(Date date, int minutes, DateTimeUtils.TimeMode timeMode) {
        return addMinutes(date, -minutes, timeMode);
    }

    public static Date addSeconds(Date date, int seconds) {
        return addSeconds(date, seconds, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date addSeconds(Date date, int seconds, DateTimeUtils.TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (seconds != 0) {
            calendar.add(13, seconds);
        }

        return calendar.getTime();
    }

    public static Date minusSeconds(Date date, int seconds) {
        return addSeconds(date, -seconds, DateTimeUtils.TimeMode.NOW_OF_DAY);
    }

    public static Date minusSeconds(Date date, int seconds, DateTimeUtils.TimeMode timeMode) {
        return addSeconds(date, -seconds, timeMode);
    }

    public static int[] diffArray(Date beginDate, Date endDate, DateTimeUtils.TimeMode timeMode) {
        int[] array = new int[5];
        Calendar beginCalendar = getCalendar(beginDate, timeMode);
        Calendar endCalendar = getCalendar(endDate, timeMode);
        long beginMillis = beginCalendar.getTimeInMillis();
        long endMillis = endCalendar.getTimeInMillis();
        long diff = endMillis - beginMillis;
        long days = diff / 86400000L;
        long hours = diff % 86400000L / 3600000L;
        long minutes = diff % 3600000L / 60000L;
        long seconds = diff % 60000L / 1000L;
        long milliseconds = diff % 1000L;
        array[0] = (int) days;
        array[1] = (int) hours;
        array[2] = (int) minutes;
        array[3] = (int) seconds;
        array[4] = (int) milliseconds;
        return array;
    }

    public static int[] diffArray(Date date, DateTimeUtils.TimeMode timeMode) {
        return diffArray(now(), date, timeMode);
    }

    public static int diffDays(Date beginDate, Date endDate, DateTimeUtils.TimeMode timeMode) {
        Calendar beginCalendar = getCalendar(beginDate, timeMode);
        Calendar endCalendar = getCalendar(endDate, timeMode);
        long beginMillis = beginCalendar.getTimeInMillis();
        long endMillis = endCalendar.getTimeInMillis();
        long days = (endMillis - beginMillis) / 86400000L;
        return (int) days;
    }

    public static int diffDays(Date beginDate, Date endDate) {
        return diffDays(beginDate, endDate, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static int diffDays(Date date, DateTimeUtils.TimeMode timeMode) {
        return diffDays(now(), date, timeMode);
    }

    public static int diffDays(Date date) {
        return diffDays(now(), date, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static int intervalDays(Date beginDate, Date endDate) {
        return diffDays(beginDate, endDate);
    }

    public static int intervalDays(Date date) {
        return diffDays(now());
    }

    public static int spanDays(Date beginDate, Date endDate) {
        return diffDays(beginDate, endDate, DateTimeUtils.TimeMode.BEGIN_OF_DAY) + 1;
    }

    public static int spanDays(Date date) {
        return diffDays(now(), date, DateTimeUtils.TimeMode.BEGIN_OF_DAY) + 1;
    }

    public static int compare(Date date, Date otherDate, DateTimeUtils.TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        Calendar otherCalendar = getCalendar(otherDate, timeMode);
        long calendarMillis = calendar.getTimeInMillis();
        long otherCalendarMillis = otherCalendar.getTimeInMillis();
        if (calendarMillis == otherCalendarMillis) {
            return 0;
        } else {
            return calendarMillis > otherCalendarMillis ? 1 : -1;
        }
    }

    public static int compareDate(Date date, Date otherDate) {
        return compare(date, otherDate, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static int compareToNow(Date date, DateTimeUtils.TimeMode timeMode) {
        return compare(date, now(), timeMode);
    }

    public static int compareToNowDate(Date date) {
        return compareToNow(date, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static boolean sameDay(Date date, Date... otherDate) {
        if (ArrayUtils.isEmpty(otherDate)) {
            return false;
        } else {
            boolean sameDay = false;
            Date[] arr$ = otherDate;
            int len$ = otherDate.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Date e = arr$[i$];
                if (compareDate(date, e) != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public static Date beginOfYesterday() {
        Date beginOfYesterday = minusDays(beginOfToday(), 1);
        return beginOfDay(beginOfYesterday);
    }

    public static Date endOfYesterday() {
        Date endOfYesterday = minusDays(beginOfToday(), 1);
        return endOfDay(endOfYesterday);
    }

    public static Date beginOfTomorrow() {
        Date beginOfYesterday = addDays(beginOfToday(), 1);
        return beginOfDay(beginOfYesterday);
    }

    public static Date endOfTomorrow() {
        Date endOfYesterday = addDays(beginOfToday(), 1);
        return endOfDay(endOfYesterday);
    }

    public static boolean isToday(Date date) {
        return compareToNowDate(date) == 0;
    }

    public static boolean isYesterday(Date date) {
        Date yesterday = beginOfYesterday();
        return sameDay(date, yesterday);
    }

    public static boolean isTomorrow(Date date) {
        Date tomorrow = beginOfTomorrow();
        return sameDay(date, tomorrow);
    }

    public static boolean before(Date date, Date otherDate, DateTimeUtils.TimeMode timeMode) {
        int result = compare(date, otherDate, timeMode);
        return result < 0;
    }

    public static boolean before(Date date, Date otherDate) {
        return before(date, otherDate, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static boolean beforeOfNow(Date date, DateTimeUtils.TimeMode timeMode) {
        return before(date, now(), timeMode);
    }

    public static boolean beforeOfNowDate(Date date) {
        return before(date, now(), DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static boolean beforeOfTomorrow(Date date, DateTimeUtils.TimeMode timeMode) {
        Date tomorrow = addDays(now(), 1, timeMode);
        return before(date, tomorrow, timeMode);
    }

    public static boolean beforeOfTomorrowDate(Date date) {
        return beforeOfTomorrow(date, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static boolean after(Date date, Date otherDate, DateTimeUtils.TimeMode timeMode) {
        int result = compare(date, otherDate, timeMode);
        return result > 0;
    }

    public static boolean after(Date date, Date otherDate) {
        return after(date, otherDate, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static boolean afterOfNow(Date date, DateTimeUtils.TimeMode timeMode) {
        return after(date, now(), timeMode);
    }

    public static boolean afterOfNowDate(Date date) {
        return after(date, now(), DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static boolean afterOfYesterday(Date date, DateTimeUtils.TimeMode timeMode) {
        Date tomorrow = minusDays(now(), 1, timeMode);
        return after(date, tomorrow, timeMode);
    }

    public static boolean afterOfYesterdayDate(Date date) {
        return afterOfYesterday(date, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
    }

    public static boolean equals(Date date, Date otherDate, DateTimeUtils.TimeMode timeMode) {
        int result = compare(date, otherDate, timeMode);
        return result == 0;
    }

    public static boolean equalsDate(Date date, Date otherDate) {
        return sameDay(date, otherDate);
    }

    public static boolean beforeOrEquals(Date date, Date otherDate, DateTimeUtils.TimeMode timeMode) {
        int result = compare(date, otherDate, timeMode);
        return result <= 0;
    }

    public static boolean beforeOrEqualsDate(Date date, Date otherDate) {
        int result = compare(date, otherDate, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
        return result <= 0;
    }

    public static boolean afterOrEquals(Date date, Date otherDate, DateTimeUtils.TimeMode timeMode) {
        int result = compare(date, otherDate, timeMode);
        return result >= 0;
    }

    public static boolean afterOrEqualsDate(Date date, Date otherDate) {
        int result = compare(date, otherDate, DateTimeUtils.TimeMode.BEGIN_OF_DAY);
        return result >= 0;
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String format(long timestamp, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(newDate(timestamp));
    }

    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    public static String format(long timestamp) {
        return format(timestamp, DEFAULT_FORMAT);
    }

    public static String format(String pattern) {
        return format(now(), pattern);
    }

    public static String format() {
        return format(now(), DEFAULT_FORMAT);
    }

    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        try {
            return formatter.parse(dateStr);
        } catch (ParseException var4) {
            throw new RuntimeException(var4);
        }
    }

    public static Date parse(String dateStr) {
        return parse(dateStr, DEFAULT_FORMAT);
    }

    public static Date parse(long timestamp) {
        return newDate(timestamp);
    }

    public static Date parse(java.sql.Date sqlDate) {
        return newDate(sqlDate.getTime());
    }

    public static Date parse(Timestamp sqlTimestamp) {
        return newDate(sqlTimestamp.getTime());
    }

    public static java.sql.Date toSQLDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static Timestamp toSQLTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static java.sql.Date nowSQLDate() {
        return toSQLDate(now());
    }

    public static Timestamp nowSQLTimestamp() {
        return toSQLTimestamp(now());
    }

    public static List<Date> getDateList(Date beginDate, Date endDate, Integer... weekFlag) {
        List<Date> dateList = getDateList(beginDate, endDate);
        if (weekFlag != null && weekFlag.length != 0) {
            List<Integer> weekFlagList = Arrays.asList(weekFlag);
            Iterator iterator = dateList.iterator();

            while (iterator.hasNext()) {
                int week = getWeek((Date) iterator.next());
                if (!weekFlagList.contains(week)) {
                    iterator.remove();
                }
            }

            return dateList;
        } else {
            return dateList;
        }
    }

    public static List<Date> getDateList(Date beginDate, Date endDate) {
        List<Date> dateList = new ArrayList();
        Date nextDate = beginOfDay(beginDate);

        do {
            dateList.add(nextDate);
            nextDate = beginOfDay(addDays(nextDate, 1));
        } while (!after(nextDate, endDate));

        return dateList;
    }

    public static String format(String dateStr, String sourcePattern, String targetPattern) {
        Date date = parse(dateStr, sourcePattern);
        return format(date, targetPattern);
    }

    public static String format(String dateStr, String targetPattern) {
        return format(dateStr, DEFAULT_FORMAT, targetPattern);
    }

    public static enum TimeMode {
        BEGIN_OF_DAY,
        END_OF_DAY,
        NOW_OF_DAY;

        private TimeMode() {
        }
    }
}
