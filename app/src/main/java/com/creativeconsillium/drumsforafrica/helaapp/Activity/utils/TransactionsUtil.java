package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.util.Log;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.MpesaMessage;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.TransactionTotal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.LocalDate;

import java.util.Map;

import androidx.annotation.NonNull;

public class TransactionsUtil {
    static final String TAG = TransactionsUtil.class.getSimpleName();
    public static void getTransactionsByYear(){

    }
    public static void getTransactionsByDate(String Date){
        //this will be used in the d
    }
    public static void getTotalTransactionsByMonth(){
        String totalSpent = "";
        String totalReceived = "";
        getUserTransactionsReference().orderByKey().
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        MpesaMessage transactions = dataSnapshot.getValue(MpesaMessage.class);
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            String date = snapshot.child("date").getValue().toString();
                            LocalDate formattedDate = FormatUtils.formatDate(date);
                            String year = FormatUtils.getYearFromDate(formattedDate);
                            String month = FormatUtils.getMonthFromDate(formattedDate);
                            Log.i(TAG, "The dates are:: " + snapshot.child("date").getValue() + " and the year is: "
                                    + year + " the month is: " + month);
                            
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void updateSummaries(TransactionTotal transactionTotal){
        String userId = FirebaseUtils.getCurrentUser().getUid();
        final Map<String, Object> transactionsSummaries = transactionTotal.toMap();
        DatabaseReference summariesRef = getUserTransactionsReference();
        summariesRef.child("transactionSummaries").setValue(transactionsSummaries)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.i(TAG, "Transactions summaries Updated!! " + transactionsSummaries);
                        }else {
                            Log.i(TAG, "Update failed " + task.getException().getMessage());
                        }
                    }
                });
    }
    public static  DatabaseReference getUserTransactionsReference(){
        String userId = FirebaseUtils.getCurrentUser().getUid();
        return  FirebaseUtils.createOrGetDatabaseRef("transactions").child(userId);
    }

}
