package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class SmsUtils {
    // ACCESS MPESA SMS
    // ACCESS OTP IF THERE WILL BE ANY
    //the columns we want to fetch from the sms content provider
    //Get Id, address, body and date
    public static final String DATE_COLUMN = "date";
    public static final String BODY_COLUMN = "body";
    public static final String ADDRESS_COLUMN = "address";

   public static String [] smsProjections = {SmsUtils.ADDRESS_COLUMN, SmsUtils.BODY_COLUMN, SmsUtils.DATE_COLUMN};
   public static String sqlClause = null;
   public static String[] selectionAgrument = { "" };
   public static String sortOrder = "";
   public static List MpesaMessages = new ArrayList<>();


    public static  void getMpesaMessages (Context context) {
        sqlClause = SmsUtils.ADDRESS_COLUMN + " like ? ";
        selectionAgrument[0] = "MPESA";

        Uri inboxUrl = Uri.parse("content://sms/inbox");
        Cursor cursor = null;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            cursor = contentResolver.query(inboxUrl, smsProjections, sqlClause, selectionAgrument, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Mpesa Count:: " + cursor.getCount());
        if (cursor == null || cursor.getCount() == 0) {
            System.out.println("it is null");
            return;
        }
        cursor.moveToFirst();

        do {
            String sender = cursor.getString(cursor.getColumnIndex(SmsUtils.ADDRESS_COLUMN));
            String message = cursor.getString(cursor.getColumnIndexOrThrow(SmsUtils.BODY_COLUMN));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(SmsUtils.DATE_COLUMN));
            System.out.println("Sender:: " + sender + " Message ::" + message + " On:: " + date);

        } while (cursor.moveToNext());
        cursor.close();
       // return MpesaMessages;
    }
}
