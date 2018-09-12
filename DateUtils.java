
import android.util.Log;

import org.joda.time.DateTime;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Hasan Mhd Amin on 9/12/2018.
 *
 * required libraries to install:
 *  * joda-time-android: https://github.com/dlew/joda-time-android#usage
 */

public class DateUtils {

    public static long ONE_DAY_IN_MILLISECOND = 86400000;
    static SimpleDateFormat sdf = null;

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public static String convertDateToString(Date date, String format) {
        //if(sdf==null) {
        sdf = new SimpleDateFormat(format);
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

    public static  String changeDateStringFormat(String date, String oldFormat , String newFormat)
    {
        try
        {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
            cal.setTime(sdf.parse(date));
            Date calendar = cal.getTime();
            SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat, Locale.US);
            Log.e("sdf",sdf2.format(calendar.getTime()));
            return sdf2.format(calendar.getTime());

        }catch (Exception e)
        {
            e.printStackTrace();
            return oldFormat;
        }
    }


    public static DateTime CalendarToTime(Calendar calendar) {
        DateTime date = new DateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return date;
    }

    public static ArrayList<Date> generateDaysArray(Calendar startDay, Calendar endDay){
        ArrayList<Date> dateList = new ArrayList<>();
        while(startDay.compareTo(endDay) < 1){
            dateList.add(startDay.getTime());
            startDay.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dateList;
    }

    public static Calendar TimestampToCalender(String timestamp){
        long timeStamp = Long.parseLong(timestamp);
        return TimestampToCalender(timeStamp);
    }

    public static Calendar TimestampToCalender(long timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar;
    }
    

    /**
     * Gets the timestamp of the start day.
     * for example: five us the timestamp of 26/09/1993 14:07
     * and the output will be the timestamp of 26/09/1993 00:00
     *
     * @param timeInMillis the timestamp of a given date
     * @return the timestamp of the start of the day.
     */
    public static long startOfDayTimestamp(long timeInMillis) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(timeInMillis);
        cal.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero
        cal.set(Calendar.MINUTE, 0); // set minutes to zero
        cal.set(Calendar.SECOND, 0); //set seconds to zero
        return cal.getTimeInMillis();
    }
}