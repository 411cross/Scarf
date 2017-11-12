package com.example.jungle.weixin.PublicUtils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by derrickJ on 2017/11/10.
 */

public class DateUtils {

    //"created_at": "Tue May 31 17:46:55 +0800 2011"

    public static final long ONE_MINUTE = 60 * 1000;
    public static final long ONE_HOUR = 60 * ONE_MINUTE;
    public static final long ONE_DAY = 24 * ONE_HOUR;


    public static String formatDate(String dateStr) {
        String formatted = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

        try {

            Date date = simpleDateFormat.parse(dateStr);
            Date currentDate = new Date();

            long duringTime = currentDate.getTime() - date.getTime();
            int dateStatus = dayCalulator(date, currentDate);

            if (duringTime <= 10 * ONE_MINUTE) {
                formatted = "刚刚";
            } else if (duringTime < ONE_HOUR) {
                formatted = duringTime / ONE_MINUTE + "分钟前";
            } else if (dateStatus == 0) {
                formatted = duringTime / ONE_HOUR + "小时前";
            } else if (dateStatus == -1) {
                formatted = "昨天 " + DateFormat.format("HH:mm", date);
            } else if (isSameYear(date, currentDate) && dateStatus < 1) {
                formatted = DateFormat.format("MM-dd", date).toString();
            } else {
                formatted = DateFormat.format("yyyy-MM-dd", date).toString();
            }

        } catch (ParseException e) {

        }

        return formatted;

    }

    public static int dayCalulator(Date targetDate, Date currentDate) {
        Calendar targetCal = Calendar.getInstance();
        targetCal.setTime(targetDate);
        int targetDOY = targetCal.get(Calendar.DAY_OF_YEAR);

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(currentDate);
        int todayDOY = todayCal.get(Calendar.DAY_OF_YEAR);

        return targetDOY - todayDOY;
    }

    public static boolean isSameYear(Date targetDate, Date currentDate) {
        Calendar targetCal = Calendar.getInstance();
        targetCal.setTime(targetDate);
        int targetYear = targetCal.get(Calendar.YEAR);

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(currentDate);
        int thisYear = todayCal.get(Calendar.YEAR);

        return targetYear == thisYear;
    }

}
