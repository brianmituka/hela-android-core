package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.MpesaMessage;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.TransactionTotal;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TransactionsUtil {
     static final String TAG = TransactionsUtil.class.getSimpleName();
     static BigDecimal totalSpent = new BigDecimal(0.00);
     static BigDecimal totalReceived = new BigDecimal(0.00);
     static ArrayList<BigDecimal> receivedAmonts = new ArrayList<>();
     static  ArrayList<BigDecimal> spentAmounts = new ArrayList<>();
     static  BigDecimal totalYearSpent = new BigDecimal(0.00);
    static ArrayList<BigDecimal> totalYearReceivedAmounts = new ArrayList<>();
    static  ArrayList<BigDecimal> totalYearSpentAmounts = new ArrayList<>();
    static  BigDecimal totalYearReceived = new BigDecimal(0.00);


    public static void getTransactionsByDate(String Date){
        //this will be used in the d
    }

    public static void getTransactionSummary(@NonNull final View fragmentLayout){
        getUserTransactionsReference().child("transactionSummaries").addValueEventListener(new ValueEventListener() {
            @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                if (dataSnapshot.hasChildren()){
                    Object monthSpent = dataSnapshot.child("monthSpent").getValue();
                    Object monthReceived = dataSnapshot.child("monthReceived").getValue();
                    Object totalSpent = dataSnapshot.child("totalSpent").getValue();
                    Object totalReceived = dataSnapshot.child("totalReceived").getValue();
                    Log.i(TAG, "SUMMARIES " + "monthSpent " + monthSpent + "month received " + monthReceived
                    + "totalReceived " + totalReceived + "totalSpent " + totalSpent
                    );
                    TextView monthTotalReceivedView = (TextView) fragmentLayout.findViewById(R.id.monthTotalReceived);
                    TextView monthTotalSpentView = (TextView) fragmentLayout.findViewById(R.id.spentMonthAmount);
                    TextView totalYearSpentView = (TextView) fragmentLayout.findViewById(R.id.yearTotalSpent);
                    TextView totalYearReceivedView = (TextView) fragmentLayout.findViewById(R.id.yearTotalReceived);
                    String monthSpentString = "KSH " + monthSpent.toString();
                    String monthReceivedString = "KSH " + monthReceived.toString();
                    String yearSpentString = "KSH " + totalSpent.toString();
                    String yearReceivedString = "KSH " + totalReceived.toString();
                    monthTotalReceivedView.setText(monthReceivedString);
                    monthTotalSpentView.setText(monthSpentString);
                    totalYearReceivedView.setText(yearReceivedString);
                    totalYearSpentView.setText(yearSpentString);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void getTotalForCurrentMonthAndYear(@NonNull final View fragmentLayout){

        getUserTransactionsReference().
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                Object date = snapshot.child("date").getValue();
                                Object amount = snapshot.child("amount").getValue();
                                Object transactionType = snapshot.child("transactionType").getValue();
                                LocalDate formattedDate = FormatUtils.formatDate(date);
                                String transactionYear = FormatUtils.getYearFromDate(formattedDate);
                                String month = FormatUtils.getMonthFromDate(formattedDate);
                                String current = FormatUtils.getCurrentMonth();
                                String currentYear = FormatUtils.getCurrentYear();
                                if (transactionYear.equals(currentYear) && month.equals(current) ){
                                    Log.i(TAG, "Current Values " + "month spent:: " + totalSpent + " month received " + totalReceived);
                                   // Log.i(TAG, "The type is " + transactionTYpe );
                                    if (transactionType!=null&&amount!=null)
                                    if (transactionType.equals("out")){
                                        spentAmounts.add(FormatUtils.formatMpesaAmount(amount));
                                        totalSpent = totalSpent.add(FormatUtils.formatMpesaAmount(amount));

                                        Log.i(TAG, "totalSpent " + totalSpent + " date:: " + date);

                                    }else if (transactionType.equals("in")){
                                            receivedAmonts.add(FormatUtils.formatMpesaAmount(amount));
                                        totalReceived = totalReceived.add(FormatUtils.formatMpesaAmount(amount));
                                       Log.i(TAG, "total Received " + totalReceived + " date:: " + date);

                                    }
                                }
                                if (currentYear.equals(transactionYear)){
                                    Log.i(TAG, "Current Values " + "year spent:: " + totalYearSpent + " year received " + totalYearReceived  );
                                    if (transactionType!=null&&amount!=null)
                                        if (transactionType.equals("out")){
                                            totalYearSpentAmounts.add(FormatUtils.formatMpesaAmount(amount));
                                            totalYearSpent = totalYearSpent.add(FormatUtils.formatMpesaAmount(amount));
                                            Log.i(TAG, "totalYearSpent " + totalYearSpent + " year:: " + date);

                                        }else if (transactionType.equals("in")){
                                            totalYearReceivedAmounts.add(FormatUtils.formatMpesaAmount(amount));
                                            totalYearReceived = totalReceived.add(FormatUtils.formatMpesaAmount(amount));
                                            Log.i(TAG, "totalYearReceived " + totalYearReceived + " date:: " + date);

                                        }
                                }
                            }
                            String monthReceivedString = totalReceived.toString();
                            String monthSpentString = totalSpent.toString();
                            String totalYearSpentString = totalYearSpent.toString();
                            String totalYearReceivedString = totalYearReceived.toString();
                            TransactionTotal transactionTotal = new TransactionTotal(monthSpentString, monthReceivedString, totalYearSpentString, totalYearReceivedString);
                            updateSummaries(transactionTotal);
                            getTransactionSummary(fragmentLayout);
                            totalReceived = new BigDecimal(0.00);
                            totalSpent = new BigDecimal(0.00);
                            totalYearReceived = new BigDecimal(0.00);
                            totalYearSpent = new BigDecimal(0.00);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i(TAG, databaseError.getMessage());

                    }
                });

    }

    public static void updateSummaries(TransactionTotal transactionTotal){
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
    public static void updateUITransactions(@NonNull View fragmentLayout){



    }
    public static  DatabaseReference getUserTransactionsReference(){
        String userId = FirebaseUtils.getCurrentUser().getUid();
        return  FirebaseUtils.createOrGetDatabaseRef("transactions").child(userId);
    }

}
