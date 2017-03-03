package io.github.shredktp.a51networkspeed;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Korshreddern on 01-Mar-17.
 */

public class MyUtility {
    public final static String FORMAT_DATE_TIME_FULL = "yyyy/MM/dd - HH:mm:ss.SSS";

    public static String getCurrentDateTimeInFormat() {
        return convertMillisToFormat(System.currentTimeMillis(), FORMAT_DATE_TIME_FULL);
    }

    private static String convertMillisToFormat(long milliSecond, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTimeInMillis());
    }
}
