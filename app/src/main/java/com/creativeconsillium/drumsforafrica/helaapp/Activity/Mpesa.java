package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.MpesaMessage;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FirebaseUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.SmsUtils;

public class Mpesa extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
          String TAG = Mpesa.class.getSimpleName();
       if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
           SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
           for (SmsMessage mesage : messages) {
               Log.i(TAG, "The address from the message is:: " + mesage.getOriginatingAddress());
               if (mesage.getOriginatingAddress().equals("MPESA")){
                   Log.i(TAG, "A new Mpesa Message has arrived");
                   String messagebody = mesage.getMessageBody();
                   String date = SmsUtils.extractMpesaDate(messagebody);
                   String amount = SmsUtils.extractMpesaAmount(messagebody);
                   String transactionCode = SmsUtils.extractMpesaCode(messagebody);
                   String transactionType = SmsUtils.mpesaMessageType(messagebody);
                   MpesaMessage messageToUpload = new MpesaMessage(transactionCode, amount, date, transactionType);
                   SmsUtils.uploadMessageToFirebase(messageToUpload);
               }else {
                   Log.i(TAG, "It is not an mpesa message");
               }

           }
       }
    }
}
