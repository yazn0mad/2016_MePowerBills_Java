package com.yazapps.mepowerbills;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StaticData {

    static boolean isFirstTime;

    static boolean isMyFirst;
    static boolean isDetailed;
    static boolean isMetered;
    static boolean isWinter; // Nov - Feb

    static double discountRate = -1.0;
    static double levyRate = 1.0;

    static Date startDate;
    static Date endDate;
    static Date currentDate;
    static Date lastDate;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    // Get Date from string
    static Date getDateFromString(String string) throws ParseException {
        return dateFormat.parse(string);
    }

    static void fromStringToDate(String string) {
        try {
            dateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    static String getStringFromDate (Date date){
        return dateFormat.format(date);
    }

    static Date getEndDate (Date date) {
        date.setTime(date.getTime() + 30 * 60 * 60 * 24);
        return date;
    }

    static boolean checkWinterMonths(int myMonth) {
        Calendar calender = new GregorianCalendar();
        //int month = calender.get(Calendar.MONTH);
        switch (myMonth) {
            case 0: // January
                return true;

            case 1:
                return true;

            case 2:
                return false;

            case 3:
                return false;

            case 4:
                return false;

            case 5:
                return false;

            case 6:
                return false;

            case 7:
                return false;

            case 8:
                return false;

            case 9:
                return false;

            case 10:
                return true;

            case 11:
                return true;

                default:
                    return false;
        }
    }

    static int getMonth (Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }
}
