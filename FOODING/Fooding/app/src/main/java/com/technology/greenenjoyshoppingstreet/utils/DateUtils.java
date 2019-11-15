package com.technology.greenenjoyshoppingstreet.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @version V1.0
 * @Title: Date utils
 * @date 2017.05.23
 */
public class DateUtils {
    /**
     * Format.
     */
    private static SimpleDateFormat format = new SimpleDateFormat();

    /**
     * Gets format.
     *
     * @param pattern the pattern
     * @return the format
     */
    private static SimpleDateFormat getFormat(String pattern) {
        format.applyPattern(pattern);
        return format;

    }

    /**
     * Pattern one.
     */
    private static final String PATTERN_ONE = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_TWO = "yy-MM-dd";
    private static final String PATTERN_FOUR = "yyyyMMddHHmmss";


    private static final String PATTERN_THREE = "MM月dd日 E";

    public static String getSubTime() {
        try {
            SimpleDateFormat format = getFormat(PATTERN_FOUR);
            return format.format(Calendar.getInstance().getTime());
        } catch (Exception e) {

        }
        return null;

    }

    public static String getTodayDate() {
        try {
            SimpleDateFormat format = getFormat(PATTERN_THREE);
            return format.format(Calendar.getInstance().getTime());
        } catch (Exception e) {

        }
        return null;

    }

    public static long parseDateMS(String data) {
        try {
            SimpleDateFormat format = getFormat(PATTERN_ONE);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(data));
            return calendar.getTimeInMillis();
        } catch (Exception e) {

        }
        return 0;
    }

    /**
     * Parse date string calendar.
     *
     * @param data the data
     * @return the calendar
     */
    public static Calendar parseDateString(String data) {
        try {
            SimpleDateFormat format = getFormat(PATTERN_ONE);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(data));
            return calendar;
        } catch (Exception e) {

        }
        return Calendar.getInstance();
    }

    public static String parseStringToDate(String data) {
        try {
            SimpleDateFormat format = getFormat(PATTERN_ONE);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(data));
            return getFormat(PATTERN_TWO).format(calendar.getTime());
        } catch (Exception e) {

        }
        return Calendar.getInstance().toString();
    }

    public static String addZeroPrefix(int time) {
        if (time < 10) {
            return "0" + time;
        } else {
            return String.valueOf(time);
        }
    }

    /**
     * Gets today start time.
     *
     * @return the today start time
     */
    public static Calendar getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

}
