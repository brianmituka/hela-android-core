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
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.TransactionsUtil;

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
                   MpesaMessage messageToUpload = new MpesaMessage();
                   messageToUpload.setTransactionTyp(SmsUtils.mpesaMessageType(messagebody));
                   messageToUpload.setTransactionCode(SmsUtils.extractMpesaCode(messagebody));
                   messageToUpload.setDate(SmsUtils.extractMpesaDate(messagebody));
                   messageToUpload.setAmount(SmsUtils.extractMpesaAmount(messagebody));

                   //MpesaMessage messageToUpload = new MpesaMessage(transactionCode, amount, date, transactionType);
                   SmsUtils.uploadMessageToFirebase(messageToUpload);
//                   TransactionsUtil.getTotalTransactionsByMonth();
               }else {
                   Log.i(TAG, "It is not an mpesa message");
               }

           }
       }
    }
}
