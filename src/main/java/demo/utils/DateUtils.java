package demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date tommorow() {
        Calendar.getInstance().add(Calendar.DATE, 1);
        return Calendar.getInstance().getTime();
    }

    public static Date now() {
        return new Date();
    }

    /**
     * Checks if param date is already passed in comparision to now
     *
     * @param deadline this date will be verified against now
     * @return True if deadline occurred, False otherwise
     */
    public static boolean isTimeout(Date deadline) {
        return new Date().compareTo(deadline) > 0;
    }
}
