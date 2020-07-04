import android.icu.util.GregorianCalendar;
import android.util.Log;

import org.joda.time.DateTime;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hasan Mhd Amin on 9/12/2018.
 * <p>
 * required libraries to install:
 * * joda-time-android: https://github.com/dlew/joda-time-android#usage
 */

public class DateUtils {
    private static final String TAG = "DateUtils";
    public static long ONE_DAY_IN_MILLISECOND = 86400000;
    static SimpleDateFormat sdf = null;

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public static String convertDateToString(Date date, String format) {
        //if(sdf==null) {
        sdf = new SimpleDateFormat(format);
//        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        //}
        //sdf.applyPattern(format);
        return sdf.format(date);
    }

    public static Date convertStringToDate(String date, String format) {
        if (sdf == null) {
            sdf = new SimpleDateFormat();
        }
        sdf.applyPattern(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String changeDateStringFormat(String date, String oldFormat, String newFormat) {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
            cal.setTime(sdf.parse(date));
            Date calendar = cal.getTime();
            SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat, Locale.US);
            Log.e("sdf", sdf2.format(calendar.getTime()));
            return sdf2.format(calendar.getTime());

        } catch (Exception e) {
            e.printStackTrace();
            return oldFormat;
        }
    }


    public static DateTime calendarToTime(Calendar calendar) {
        DateTime date = new DateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        return date;
    }

    public static ArrayList<Date> generateDaysArray(Calendar startDay, Calendar endDay) {
        ArrayList<Date> dateList = new ArrayList<>();
        while (startDay.compareTo(endDay) < 1) {
            dateList.add(startDay.getTime());
            startDay.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dateList;
    }

    public static Calendar timestampToCalender(String timestamp) {
        long timeStamp = Long.parseLong(timestamp);
        return timestampToCalender(timeStamp);
    }

    public static Calendar timestampToCalender(long timestamp) {
        //TODO: check for timezone, leap month, etc. in manual conversion.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar;
    }
    
    /**
     * Gets the timestamp of the start day.
     * for example: the timestamp of 26/09/1993 14:07
     * and the output will be the timestamp of 26/09/1993 00:00
     *
     * @param timeInMillis the timestamp of a given date
     * @return the timestamp of the start of the day.
     */
    public static long startOfDayTimestamp(long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);

        cal.set(Calendar.MILLISECOND,0);

        cal.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero

        cal.set(Calendar.MINUTE, 0); // set minutes to zero

        cal.set(Calendar.SECOND, 0); //set seconds to zero

        cal.set(Calendar.MILLISECOND, 0); //set milliseconds to zero

        return cal.getTimeInMillis();
    }

    public static long startOfDayLocalTimestamp(long timeInMillis) {
        return convertUtcTimestampToLocalTime(startOfDayTimestamp(timeInMillis));
    }


    public static String splitToComponentTimes(float longVal) {
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

//        int[] ints = {hours , mins , secs};
        String result = hours + ":" + mins + ":" + secs;
        return result;
    }

    public static Long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    public static Long getLocalCurrentTimeMillis(){
        long s = System.currentTimeMillis();
        int offset = TimeZone.getDefault().getRawOffset() + TimeZone.getDefault().getDSTSavings();
        return s + offset;
    }

    public static Long convertUtcTimestampToLocalTime(long UtcTimestamp){
        int offset = TimeZone.getDefault().getRawOffset() + TimeZone.getDefault().getDSTSavings();
        return UtcTimestamp + offset;
    }

    public static String convertTimestampToReadableDuration(long timestampDuration){
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timestampDuration),
                TimeUnit.MILLISECONDS.toMinutes(timestampDuration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timestampDuration)),
                TimeUnit.MILLISECONDS.toSeconds(timestampDuration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timestampDuration)));

        return hms;
    }
}
