package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.util.Log;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FormatUtils {
    public static String month;
    public static String year;
    public static String currentMonth;
    public static String currentYear;
    static String TAG = FormatUtils.class.getSimpleName();
    public static LocalDate formatDate(String date){
       LocalDate formattedDate;
       String pattern = "dd/M/yy";
       DateTimeFormatter format = getFormatter(pattern).withLocale(Locale.GERMANY);
       formattedDate = LocalDate.parse(date, format);
       return formattedDate;
    }

    public static BigDecimal formatMpesaAmount(String amount){
        BigDecimal formattedAmount;
        formattedAmount = new BigDecimal(amount);
        return formattedAmount;

    }
    public static String getMonthFromDate(LocalDate date){
       // DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM");
        month = getFormatter("MMM").print(date);
        Log.i(TAG, "the extracted month is " + month);
       return month;
    }
    public static String getYearFromDate(LocalDate date){
        year = getFormatter("yyy").print(date);
        Log.i(TAG, "the extracted year is " + year);
        return year;

    }

    /**
     * Format date based on a pattern like dd/MM/yyy or MMM(to get the month)
     * @param pattern
     * @return
     */
    public static DateTimeFormatter getFormatter(String pattern){
        return DateTimeFormat.forPattern(pattern);
    }
    public static String getCurrentMonth(){
        LocalDate date = LocalDate.now();
        currentMonth = getFormatter("MMM").print(date);
        Log.i(TAG, "the current month is " + currentMonth);
        return currentMonth;
    }
    public static String getCurrentYear(){
        LocalDate date = LocalDate.now();
        currentYear = getFormatter("yyy").print(date);
        Log.i(TAG, "the current year is:: " + currentYear);
        return currentYear;

    }
}
