package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.MpesaMessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
   static BigDecimal formattedAmount;
   static String transactionType;
   static String transactionCode;
   static LocalDate formattedDate;
   static String mpesaAmount;
    static String mpesaMessageDate;

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
           // System.out.println("Sender:: " + sender + " Message ::" + message + " On:: " + date);
            Log.i(TAG, "code:: " + extractMpesaCode(message) + " date " + extractMpesaDate(message) +
                  " amount:: " + extractMpesaAmount(message) + " type:: " + mpesaMessageType(message) + " message "   );
            MpesaMessage mpesaMessage = new MpesaMessage(extractMpesaCode(message), extractMpesaAmount(message)      , extractMpesaDate(message), mpesaMessageType(message));
            uploadMessageToFirebase(mpesaMessage);
            //extractMpesaCode(message);
            //extractMpesaDate(message);
            //extractMpesaAmount(message);
            //mpesaMessageType(message);


        } while (cursor.moveToNext());
        cursor.close();
       // return MpesaMessages;
    }

    //Create methods to sort the message based on the messagebody;
    public static boolean getIncomingMpesaMessages(String message){
        boolean found = false;
        String[] moneyInKeyWords = {
                "received",
        };
        for (int i = 0; i <moneyInKeyWords.length ; i++) {
            Pattern keywordPattern = Pattern.compile(moneyInKeyWords[i]);
            found = keywordPattern.matcher(message).find();
            if (found) {
                return found; 
            }
        }
        return found;
    }
    public static boolean getOutgoingMpesaMessages(String message){
        boolean found = false;
        String[] moneyOutKeywords = {
          "sent","bought","AMWithdraw","paid","PMWithdraw"
        };
        for (int i = 0; i <moneyOutKeywords.length ; i++) {
            Pattern moneyOutPattern = Pattern.compile(moneyOutKeywords[i]);
            found = moneyOutPattern.matcher(message).find();
            if (found) {
                return found; 
            }

        }
        return found;
    }

    public static String mpesaMessageType(String message){

        if (getOutgoingMpesaMessages(message)){
           transactionType = "out";
          // Log.i(TAG, "the transaction::moneyout " + message + "is " + transactionType );
        }
        if (getIncomingMpesaMessages(message)){
          transactionType = "in";
           // Log.i(TAG, "the transaction::moneyin " + message + "is " + transactionType );
        }

        return transactionType;
    }

    public static String extractMpesaCode(String message){
        String transactionCoderegex = "^[A-Za-z0-9]\\w+.";
        Pattern mpesaCodePattern = Pattern.compile(transactionCoderegex);
        Matcher m = mpesaCodePattern.matcher(message);
        if (m.find()) {
            //Log.i(TAG, mpesaCodePattern + " matches " + m.group(0) + " " + message);
            /**
             * m.group(0) returns the matched character
             */
            transactionCode  = m.group(0);
           // Log.i(TAG, "the transaction code is:: " + transactionCode + " " + message);
        } else {
            Log.i(TAG, "No match found");

        }
        return transactionCode;
    }


    public static String extractMpesaDate(String message){
        String mpesaDateRegex = "[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}";
        Pattern mpesaDatePattern = Pattern.compile(mpesaDateRegex);
        Matcher m = mpesaDatePattern.matcher(message);
        if (m.find()){
            //Log.i(TAG, mpesaDatePattern + " matches " + m.group(0) + " " + message);
            /**
             * m.group(0) returns the matched character
             */
            mpesaMessageDate  = m.group(0);
            formattedDate = FormatUtils.formatDate(mpesaMessageDate);
            Log.i(TAG, "the formatted transaction date is::" + formattedDate);
        } else {
            Log.i(TAG, "no matches found!!");
        }

        return mpesaMessageDate;

    }
    public static String extractMpesaAmount(String message){
        String mpesaAmountRegex = "Ksh\\d+,?\\d+\\.?\\d*";
        Pattern mpesaAmountPattern = Pattern.compile(mpesaAmountRegex);
        Matcher m = mpesaAmountPattern.matcher(message);
        if (m.find()){
            //Log.i(TAG, mpesaAmountPattern + " matches " + m.group(0) + " " + message);
            mpesaAmount = m.group(0).replaceAll("Ksh|,?", "");

           // Log.i(TAG, "mpesa amount " + mpesaAmount);
            formattedAmount = FormatUtils.formatMpesaAmount(mpesaAmount);
            Log.i(TAG, "The formatted amount is:: " + formattedAmount);

           // Log.i(TAG, "The other that matches:: " + m.group(1));
           // Log.i(TAG, "The amount is:: " + mpesaAmount + " " + message);
        }else {
            Log.i(TAG, "No matches found!!!");
        }
        return mpesaAmount;
    }
    public static void uploadMessageToFirebase(final MpesaMessage message){
//        UiUtils.showDialog("Hela is setting up",activity );
        String userId = FirebaseUtils.getCurrentUser().getUid();
        String userEmail = FirebaseUtils.getCurrentUser().getEmail();
        Log.i(TAG, "Uploading messages for " + userEmail );
        Map<String, Object> messageValues = message.toMap();
        DatabaseReference transactionReference = FirebaseUtils.createDatabaseRef("transactions");
        transactionReference.child(userId).push().updateChildren(messageValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.i(TAG, "Message uploaded successfully" + message);
                }else{
                    Log.i(TAG, "An error occured" + task.getException().getMessage());
                }

            }
        });


    }
}
