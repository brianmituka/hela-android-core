package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.util.Log;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FormatUtils {
    public static LocalDate formatDate(String date){
       LocalDate formattedDate;
       DateTimeFormatter format = DateTimeFormat.forPattern("dd/M/yy").withLocale(Locale.GERMANY);
       Log.i(TAG, "the date String is:: " + date + " " + format);
       formattedDate = LocalDate.parse(date, format);
       return formattedDate;
    }

    public static BigDecimal formatMpesaAmount(String amount){
        BigDecimal formattedAmount;
        formattedAmount = new BigDecimal(amount);
        return formattedAmount;

    }
}
