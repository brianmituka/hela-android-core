package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelBudgets;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelTransactionsReceived;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.MonthSummary;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.MpesaMessage;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.TransactionTotal;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Mpesa;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TransactionsUtil {
     static final String TAG = TransactionsUtil.class.getSimpleName();
     static BigDecimal totalSpent = new BigDecimal(0.00);
     static BigDecimal totalReceived = new BigDecimal(0.00);
     static  BigDecimal totalYearSpent = new BigDecimal(0.00);
    static ArrayList<BigDecimal> totalYearReceivedAmounts = new ArrayList<>();
    static  ArrayList<BigDecimal> totalYearSpentAmounts = new ArrayList<>();
    static  BigDecimal totalYearReceived = new BigDecimal(0.00);
    static String currentYear = FormatUtils.getCurrentYear();
   static BigDecimal monthTotalReceived = new BigDecimal(0.00);
    static BigDecimal monthTotalReceivedUpdated = new BigDecimal(0.00);
   static BigDecimal monthTotalSpent = new BigDecimal(0.00);
    static BigDecimal monthTotalSpentUpdated = new BigDecimal(0.00);
    public static List<MpesaMessage> userReceivedTransactions = new ArrayList<>();
    public static List<MpesaMessage> userReceivedTransactionsUpdated = new ArrayList<>();
    public static List<MpesaMessage> userSpentTransactions = new ArrayList<>();
    public static List<MpesaMessage> userSpentTransactionsUpdated = new ArrayList<>();


    public static void getTransactionsReceivedByMonth(final String month, final String year){
        getUserTransactionsReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Object date = snapshot.child("date").getValue();
                        Object transactionType = snapshot.child("transactionType").getValue();
                        LocalDate formattedDate = FormatUtils.formatDate(date);
                        String transactionYear = FormatUtils.getYearFromDate(formattedDate);
                        String TransactionMonth = FormatUtils.getMonthFromDate(formattedDate);
                        Log.i(TAG, "The month and Year are:: " + month + year);
//                        if (formattedDate == Date){
//                            if (transactionType == "in"){
//                                MpesaMessage transaction = snapshot.getValue(MpesaMessage.class);
//                                userReceivedTransactions.add(transaction);
//
//                            }
//                        }

                    }
                    userReceivedTransactionsUpdated = userReceivedTransactions;
                    userReceivedTransactions = new ArrayList<>();
                }else{
                    userReceivedTransactions = new ArrayList<>();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public static BigDecimal getSpentTransactionsByMonth(final String month){
//
        getUserTransactionsReference().child("transactionSummaries").child("allmonthsSpent").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "I was hit " + month);
                if (dataSnapshot.hasChild(month)){
                    Log.i(TAG, "Month is " + month);
                    Object total = dataSnapshot.child(month).getValue();
                    if (total!=null){
                        monthTotalSpent = FormatUtils.formatMpesaAmount(total);
                        monthTotalSpentUpdated = monthTotalSpent;
                        Log.i(TAG, " month spent " + monthTotalSpent);
                    }
                }

                monthTotalSpent = new BigDecimal(0.00);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return monthTotalSpentUpdated;
    }

    public static  BigDecimal getReceivedTransactionsByMonth(final String month){
        monthTotalReceived = new BigDecimal(0.00);
        getUserTransactionsReference().child("transactionSummaries").child("allmonthsReceived").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(month)){
                    Object total = dataSnapshot.child(month).getValue();
                    if (total!=null)
                        monthTotalReceived = FormatUtils.formatMpesaAmount(total);
                    Log.i(TAG, " month received>>>> " + monthTotalReceived);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      return monthTotalReceived;
    }
    public static void getMonthSpentAmount(){
       final String[] monthsArray = {
          "JAN",
          "FEB",
          "MAR",
          "APR",
          "MAY",
          "JUN",
          "JULY",
          "AUG",
          "SEP",
          "OCT",
          "NOV",
          "DEC"
        };
        final Multimap<String, BigDecimal> foundSpentTransactions = ArrayListMultimap.create();
        getUserTransactionsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Object date = snapshot.child("date").getValue();
                        Object amount = snapshot.child("amount").getValue();
                        Object transactionType = snapshot.child("transactionType").getValue();
                        LocalDate formattedDate = FormatUtils.formatDate(date);
                        String transactionYear = FormatUtils.getYearFromDate(formattedDate);
                        String TransactionMonth = FormatUtils.getMonthFromDate(formattedDate);
                        if (transactionYear.equals(currentYear)){
                            for (String month : monthsArray) {
                                Log.i(TAG, "month in loop " + month + "TransactionMonth " + FormatUtils.getMonthFromDate(formattedDate));
                                if (TransactionMonth.toLowerCase().equals(month.toLowerCase())) {
                                    Log.i(TAG, "The current year is " + currentYear + "transaction Year " + transactionYear);
                                    if (transactionType != null) {
                                        if (transactionType.equals("out")) {
                                            Log.i(TAG, "I was hit again");
                                            if (amount != null)
                                            foundSpentTransactions.put(month, FormatUtils.formatMpesaAmount(amount));
                                        }
                                    }
                                }
                            }

                        }


                    }
                }
                final HashMap<String, String> cleanSpentTransactions = new HashMap<>();
                for (String month: foundSpentTransactions.keySet()) {
                    BigDecimal spentAmount = new BigDecimal(0.00);
                    Collection<BigDecimal> amounts = foundSpentTransactions.get(month);
                    for (BigDecimal amount: amounts){
                        spentAmount = spentAmount.add(amount);
                    }
                    cleanSpentTransactions.put(month, spentAmount.toString());
                    Log.i(TAG, "received amount is>>>>> " + spentAmount + " for " + month + currentYear);
                }
                setMonthlySpentSummaries(cleanSpentTransactions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static void getMonthReceivedAmount(){
        final String[] monthsArray = {
                "JAN",
                "FEB",
                "MAR",
                "APR",
                "MAY",
                "JUN",
                "JUL",
                "AUG",
                "SEP",
                "OCT",
                "NOV",
                "DEC"
        };

        final Multimap<String, BigDecimal> foundTransactions = ArrayListMultimap.create();
        getUserTransactionsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Object date = snapshot.child("date").getValue();
                        Object amount = snapshot.child("amount").getValue();
                        Object transactionType = snapshot.child("transactionType").getValue();
                        LocalDate formattedDate = FormatUtils.formatDate(date);
                        String transactionYear = FormatUtils.getYearFromDate(formattedDate);
                        String TransactionMonth = FormatUtils.getMonthFromDate(formattedDate);
                        Log.i(TAG, "I was hit");
                        if (transactionYear.equals(currentYear)){
                            for (String month : monthsArray) {
                                Log.i(TAG, "month in loop " + month + "TransactionMonth " + FormatUtils.getMonthFromDate(formattedDate));
                            if (TransactionMonth.toLowerCase().equals(month.toLowerCase())) {
                                Log.i(TAG, "The current year is " + currentYear + "transaction Year " + transactionYear);
                                if (transactionType != null) {
                                    if (transactionType.equals("in")) {
                                            Log.i(TAG, "I was hit again");
                                            if (amount != null)
                                        foundTransactions.put(month, FormatUtils.formatMpesaAmount(amount));
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                final HashMap<String, String> cleanTransactions = new HashMap<>();
                for (String month: foundTransactions.keySet()) {
                    BigDecimal receivedAmount = new BigDecimal(0.00);
                    Collection<BigDecimal> amounts = foundTransactions.get(month);
                      for (BigDecimal amount: amounts){
                           receivedAmount = receivedAmount.add(amount);
                      }
                      cleanTransactions.put(month, receivedAmount.toString());
                    Log.i(TAG, "received amount is>>>>> " + receivedAmount + " for " + month + currentYear);
                }
                setMonthlyReceivedSummaries(cleanTransactions);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                    TextView budgetSpentAmount = (TextView) fragmentLayout.findViewById(R.id.textAmount);
                    String monthSpentString = "KSH " + monthSpent.toString();
                    String monthReceivedString = "KSH " + monthReceived.toString();
                    String yearSpentString = "KSH " + totalSpent.toString();
                    String yearReceivedString = "KSH " + totalReceived.toString();
                    monthTotalReceivedView.setText(monthReceivedString);
                    monthTotalSpentView.setText(monthSpentString);
                    totalYearReceivedView.setText(yearReceivedString);
                    totalYearSpentView.setText(yearSpentString);
                    budgetSpentAmount.setText(monthSpentString);

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
                                if (transactionYear!=null){
                                    if (transactionYear.equals(currentYear)){
                                        Log.i(TAG, "Current Values " + "month spent:: " + totalSpent + " month received " + totalReceived);
                                        // Log.i(TAG, "The type is " + transactionTYpe );
                                        if (month.equals(current))
                                            if (amount!=null)
                                                if (transactionType!=null)
                                                    if (transactionType.equals("out")){
//                                        spentAmounts.add(FormatUtils.formatMpesaAmount(amount));
                                                        totalSpent = totalSpent.add(FormatUtils.formatMpesaAmount(amount));

                                                        Log.i(TAG, "totalSpent " + totalSpent + " date:: " + date);

                                                    }else if (transactionType.equals("in")){
//                                            receivedAmonts.add(FormatUtils.formatMpesaAmount(amount));
                                                        totalReceived = totalReceived.add(FormatUtils.formatMpesaAmount(amount));
                                                        Log.i(TAG, "total Received " + totalReceived + " date:: " + date);

                                                    }
                                    }
                                    if (currentYear.equals(transactionYear)){
                                            Log.i(TAG, "Current Values " + "year spent:: " + totalYearSpent + " year received " + totalYearReceived  );
                                        if (transactionType!=null)
                                            if (transactionType.equals("out")){
                                                if (amount!=null)
//                                                totalYearSpentAmounts.add(FormatUtils.formatMpesaAmount(amount));
                                                totalYearSpent = totalYearSpent.add(FormatUtils.formatMpesaAmount(amount));
                                                Log.i(TAG, "totalYearSpent " + totalYearSpent + " year:: " + date);

                                            }else if (transactionType.equals("in")){
                                                if (amount!=null)
//                                                totalYearReceivedAmounts.add(FormatUtils.formatMpesaAmount(amount));
                                                totalYearReceived = totalYearReceived.add(FormatUtils.formatMpesaAmount(amount));
                                                Log.i(TAG, "totalYearReceived " + totalYearReceived + " date:: " + date);

                                            }
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
    public static void setMonthlyReceivedSummaries(final HashMap<String, String> allMonthsReceived){
        DatabaseReference summariesRef = getUserTransactionsReference();
        summariesRef.child("transactionSummaries").child("allmonthsReceived").setValue(allMonthsReceived)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.i(TAG, "All Month summaries Updated!! " + allMonthsReceived);
                        }else {
                            Log.i(TAG, "Update failed " + task.getException().getMessage());
                        }
                    }
                });
    }
    public static void setMonthlySpentSummaries(final HashMap<String, String> allMonthsSpent){
        DatabaseReference summariesRef = getUserTransactionsReference();
        summariesRef.child("transactionSummaries").child("allmonthsSpent").setValue(allMonthsSpent)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.i(TAG, "All Month summaries Updated!! " + allMonthsSpent);
                        }else {
                            Log.i(TAG, "Update failed " + task.getException().getMessage());
                        }
                    }
                });
    }
   public static void addReceivedTransaction(String date, String amount, String name, String transactionType){
       MpesaMessage transaction = new MpesaMessage();
       transaction.setDate(date);
       transaction.setAmount(amount);
       transaction.setName(name);
       transaction.setTransactionTyp(transactionType);
       SmsUtils.uploadMessageToFirebase(transaction);
   }
    public static  DatabaseReference getUserTransactionsReference(){
        String userId = FirebaseUtils.getCurrentUser().getUid();
        return  FirebaseUtils.createOrGetDatabaseRef("transactions").child(userId);
    }

}
