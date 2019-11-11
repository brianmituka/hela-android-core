package com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter.AdapterRVTransactionsDate;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter.AdapterRVTransactionsSpent;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelTransactionsDate;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelTransactionsSpent;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class FragmentTransactionsSpent extends Fragment {

    private TextView txtTransactionMonthYear;

    private RecyclerView rvTransactionsDate;

    private EditText edSpentTime;

    private Context coxContext;

    private BottomSheetBehavior bsbAddTransactionBottomSheet;
    private BottomSheetBehavior bsbTransactionAddToBudgetBottomSheet;

    private List<ModelTransactionsSpent> lsTransactionSpent;
    private List<ModelTransactionsDate> lsTransactionsDate;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.coxContext = context;

    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragTransactionSpent =inflater.inflate(R.layout.fragment_transactions_tab_spent, container,false);

        initializeVariablesAndUIObjects(vFragTransactionSpent);

        return vFragTransactionSpent;
    }


    /**
     * Method to declare and initialize class variables and UI Objects used in this Fragment.
     *
     * Called In:
     *          - (Override) this.onCreateView();
     *
     * @param fragmentLayout                                    (View)
     */
    private void initializeVariablesAndUIObjects(@NonNull View fragmentLayout) {

        ConstraintLayout conslayTransactionMonthYear = (ConstraintLayout) fragmentLayout.findViewById(R.id.conslayTransactionsSpentMonthYear);
        conslayTransactionMonthYear.setOnClickListener(clkTransactions);

        txtTransactionMonthYear = (TextView) fragmentLayout.findViewById(R.id.txtTransactionsSpentMonthYear);
        txtTransactionMonthYear.setText(codeToGetCurrentMonthAndYear());

        Calendar calMonthSelected = new GregorianCalendar(Locale.getDefault());
        int iCurrentMonth = calMonthSelected.get(Calendar.MONTH);
        int iCurrentYear = calMonthSelected.get(Calendar.YEAR);
        AdapterRVTransactionsDate clsAdapterTransactionsDate = new AdapterRVTransactionsDate(coxContext, codeToGetDatesInSelectedMonthYear(iCurrentMonth, iCurrentYear));

        rvTransactionsDate = (RecyclerView) fragmentLayout.findViewById(R.id.recyclerViewDate);
        RecyclerView.LayoutManager rvlmLayoutManagerDates = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTransactionsDate.setLayoutManager(rvlmLayoutManagerDates);
        rvTransactionsDate.setAdapter(clsAdapterTransactionsDate);

        lsTransactionSpent = new ArrayList<>();
        codeToPrepareTransactionsSpentData();

        AdapterRVTransactionsSpent clsAdapterTransactionSpent = new AdapterRVTransactionsSpent(coxContext, lsTransactionSpent);

        RecyclerView rvTransactionSpent = (RecyclerView) fragmentLayout.findViewById(R.id.recyclerViewSTransactions);
        RecyclerView.LayoutManager rvlmLayoutManager = new LinearLayoutManager(getActivity());
        rvTransactionSpent.setLayoutManager(rvlmLayoutManager);
        rvTransactionSpent.setAdapter(clsAdapterTransactionSpent);

        Button btnAddSpentTransaction = fragmentLayout.findViewById(R.id.btnTransactionsAddSpent);
        btnAddSpentTransaction.setOnClickListener(clkTransactions);

        View vAddTransactionBottomSheet = fragmentLayout.findViewById(R.id.nsvTransactionsAddSpent);
        bsbAddTransactionBottomSheet = BottomSheetBehavior.from(vAddTransactionBottomSheet);
        bsbAddTransactionBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bsbAddTransactionBottomSheet.setPeekHeight(0);
        bsbAddTransactionBottomSheet.setHideable(false);

        initializeVariablesAndUIObjectsInAddSpentTransactionBottomSheet(vAddTransactionBottomSheet);

        View vTransactionAddToBudgetBottomSheet = fragmentLayout.findViewById(R.id.nsvTransactionSpentAddToBudget);
        bsbTransactionAddToBudgetBottomSheet = BottomSheetBehavior.from(vTransactionAddToBudgetBottomSheet);
        bsbTransactionAddToBudgetBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bsbTransactionAddToBudgetBottomSheet.setPeekHeight(0);
        bsbTransactionAddToBudgetBottomSheet.setHideable(false);

        initializeVariablesAndUIObjectsInTransactionSpentAddToBudgetBottomSheet(vTransactionAddToBudgetBottomSheet);

    }

    /**
     * Method to declare and initialize variables and UI Objects referenced in the Add Spent Transaction Bottom Sheet.
     *
     * Called In:
     *          - this.initializeVariablesAndUIObjects();
     *
     * @param bottomSheetLayout                                     (View) The Add Spent Transaction Bottom Sheet Layout.
     */
    private void initializeVariablesAndUIObjectsInAddSpentTransactionBottomSheet(@NonNull View bottomSheetLayout) {

        ImageView imgvClose = (ImageView) bottomSheetLayout.findViewById(R.id.imgvAddTransactionSpentClose);
        imgvClose.setOnClickListener(clkAddSpentTransaction);

        edSpentTime = (EditText) bottomSheetLayout.findViewById(R.id.edAddTransactionSpentTime);
        edSpentTime.setOnClickListener(clkAddSpentTransaction);

        Button btnSaveTransaction = (Button) bottomSheetLayout.findViewById(R.id.btnAddTransactionSpentSave);
        btnSaveTransaction.setOnClickListener(clkAddSpentTransaction);

    }

    /**
     * Method to declare and initialize variables and UI Objects referenced in the Add Spent Transaction to Budget Bottom Sheet.
     *
     * Called In:
     *          - this.initializeVariablesAndUIObjects();
     *
     * @param bottomSheetLayout                                     (View) The Add Spent Transaction to Budget Bottom Sheet Layout.
     */
    private void initializeVariablesAndUIObjectsInTransactionSpentAddToBudgetBottomSheet(@NonNull View bottomSheetLayout) {

        ImageView imgvClose = (ImageView) bottomSheetLayout.findViewById(R.id.imgvTransactionSpentAddToBudgetClose);
        imgvClose.setOnClickListener(clkTransactionsSpent);

        Button btnSaveTransaction = (Button) bottomSheetLayout.findViewById(R.id.btnTransactionSpentAddToBudgetSave);
        btnSaveTransaction.setOnClickListener(clkTransactionsSpent);

    }

    /**
     * Method to get the current month name and year and return them as one String.
     *
     * Called In:
     *          - this.initializeVariablesAndUIObjects();
     *
     * @return                                              Current month and year.
     */
    private String codeToGetCurrentMonthAndYear() {

        Calendar calMonthSelected = new GregorianCalendar(Locale.getDefault());
        Date dateCurrentDate = calMonthSelected.getTime();

        SimpleDateFormat sdfCurrentMonthYear = new SimpleDateFormat("MMMM YYYY", Locale.getDefault());

        return (sdfCurrentMonthYear.format(dateCurrentDate));
    }

    /**
     * Method to get Dates of the month and their respective days of the week in the selected Month in the selected year.
     * This method starts with getting a GregorianCalendar object.
     * It sets the Day of Month to the first ever possible day of month, i.e. all months always start with date 1.
     * Then it sets the month to the month that has been selected/current month.
     * Then it sets the year to the year that has been selected/current year.
     * It then creates a SimpleDateFormat with the pattern of full Day of the Week, i.e. "EEEE".
     * It then gets the total number of days for the month specified in the Calendar object that we created using "getActualMaximum()".
     * It then starts a loop for the days in the selected month.
     * In the loop, it checks the last digit of the day of the month number to determine the appropriate superscript for the date.
     * It then sets the specific day to the calendar and calls "getTime()" to get the Date object.
     * From there, the day of the Week, the day of the month and their respective superscript are combined, added to a list which is then returned!
     *
     * I looked for this kind of solution everywhere online but I couldn't find! I really should package this up and sell it! hahahaha
     *
     * Called In:
     *          - this.initializeVariablesAndUIObjects();
     *          - (Override) this.odslMonthYearPicker.onDateSet();
     *
     * @param selectedMonth                                     (int) The month to get dates for.
     * @param selectedYear                                      (int) The year to get dates for.
     * @return lsTransactionDatesOfAMonth                       (List<ModelTransactionsDate>) List of date objects.
     */
    private List<ModelTransactionsDate> codeToGetDatesInSelectedMonthYear(int selectedMonth, int selectedYear) {

        List<ModelTransactionsDate> lsTransactionDatesOfAMonth = new ArrayList<>();

        Calendar calCurrentDay = new GregorianCalendar(Locale.getDefault());
        int iCurrentDayOfMonth = calCurrentDay.get(Calendar.DAY_OF_MONTH);

        Calendar calMonthSelected = new GregorianCalendar(Locale.getDefault());
        calMonthSelected.set(Calendar.DAY_OF_MONTH, 1);
        calMonthSelected.set(Calendar.MONTH, selectedMonth);
        calMonthSelected.set(Calendar.YEAR, selectedYear);

        SimpleDateFormat sdfDatesInMonth = new SimpleDateFormat("EEEE", Locale.getDefault());

        int iDaysInSelectedMonth = calMonthSelected.getActualMaximum(Calendar.DAY_OF_MONTH);
        int iDaysAfterCurrentDateInMonth = iDaysInSelectedMonth - iCurrentDayOfMonth;
        int iDaysBeforeCurrentDateInMonth = iDaysInSelectedMonth - iDaysAfterCurrentDateInMonth;

        for (int i = 1; i <= iDaysBeforeCurrentDateInMonth; i++) {

            String sDaySuperScript = "";

            String sDayOfMonth = String.valueOf(i);
            if ((sDayOfMonth.endsWith("1")) && (!sDayOfMonth.equals("11"))) {
                sDaySuperScript = "st";
            } else if (sDayOfMonth.endsWith("2")) {
                sDaySuperScript = "nd";
            } else if ((sDayOfMonth.endsWith("3")) && (!sDayOfMonth.equals("13"))) {
                sDaySuperScript = "rd";
            } else {
                sDaySuperScript = "th";
            }

            calMonthSelected.set(Calendar.DAY_OF_MONTH, i);
            Date dateDateInMonth = calMonthSelected.getTime();

            String sNameOfDayOfWeek = i + sDaySuperScript + " " + sdfDatesInMonth.format(dateDateInMonth);

            ModelTransactionsDate clsModelTransactionsDate = new ModelTransactionsDate(sNameOfDayOfWeek, codeToCheckIfCurrentDateIsInDatesOfSelectedMonthAndYear(selectedYear, selectedMonth, i));
            lsTransactionDatesOfAMonth.add(clsModelTransactionsDate);

        }

        if (lsTransactionDatesOfAMonth.size() > 0) {
            Collections.reverse(lsTransactionDatesOfAMonth);
        }

        return lsTransactionDatesOfAMonth;
    }

    /**
     * Method to check if the current is part of the dates in the selected month and year.
     *
     * Called In:
     *          - this.codeToGetDatesInSelectedMonthYear();
     *
     * @param selectedYear                                          (int) The selected year
     * @param selectedMonth                                         (int) The selected month
     * @param dayInMonth                                            (int) The day in the selected month and year
     * @return                                                      (boolean) Is the current date part of the dates?
     */
    private boolean codeToCheckIfCurrentDateIsInDatesOfSelectedMonthAndYear(int selectedYear, int selectedMonth, int dayInMonth) {

        Calendar calCurrentDate = new GregorianCalendar(Locale.getDefault());
        int iCurrentYear = calCurrentDate.get(Calendar.YEAR);
        int iCurrentMonth = calCurrentDate.get(Calendar.MONTH);
        int iCurrentDate = calCurrentDate.get(Calendar.DAY_OF_MONTH);

        if ((iCurrentYear == selectedYear) && (iCurrentMonth == selectedMonth) && (iCurrentDate == dayInMonth)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Method to set up Month & Year Picker Dialog from MonthPickerDialog library.
     *
     * Called In:
     *          - (Override) this.clkTransactions.onClick();
     */
    private void codeToSetUpMonthYearPicker() {

        Calendar calToday = new GregorianCalendar(Locale.getDefault());

        MonthPickerDialog.Builder mpdgbldMonthPickerDialog = new MonthPickerDialog.Builder(getContext(), odslMonthYearPicker, calToday.get(Calendar.YEAR), calToday.get(Calendar.MONTH));
        mpdgbldMonthPickerDialog.setMinYear(2015);
        mpdgbldMonthPickerDialog.setMaxMonth(calToday.get(Calendar.MONTH));
        mpdgbldMonthPickerDialog.setMaxYear(calToday.get(Calendar.YEAR));
        mpdgbldMonthPickerDialog.setTitle("Select Transaction Month/Year");

        MonthPickerDialog mpdgMonthPicker = mpdgbldMonthPickerDialog.build();
        mpdgMonthPicker.show();

    }

    /**
     * Method to show a TimePickerDialog to have the user select the Add Spent Transaction Time.
     *
     * Called In:
     *          - (Override) this.clkAddSpentTransaction.onClick();
     */
    private void codeToShowTimePickerDialog() {

        Calendar calCurrentTime = Calendar.getInstance(Locale.getDefault());
        int iCurrentHour = calCurrentTime.get(Calendar.HOUR_OF_DAY);
        int iCurrentMinute = calCurrentTime.get(Calendar.MINUTE);

        TimePickerDialog tpdgTimePickerDialog = new TimePickerDialog(getContext(), otslAddTransactionTime, iCurrentHour, iCurrentMinute, false);
        tpdgTimePickerDialog.setTitle("Spent Transaction Time");
        tpdgTimePickerDialog.show();

    }

    /**
     * Method to prepare data of the Paid loans.
     *
     * Called In:
     *          - this.initializeVariablesAndUIObjects();
     */
    private void codeToPrepareTransactionsSpentData() {

        lsTransactionSpent.add(new ModelTransactionsSpent("Today 4:45 PM", "+ Add to Budget", "KSH 5,000", "Michael Mutethia Mutua", "Mpesa"));
        lsTransactionSpent.add(new ModelTransactionsSpent("Mon 9th 4:45 PM", "Car", "KSH 2,000", "Savings", "Cash"));
        lsTransactionSpent.add(new ModelTransactionsSpent("Fri 3rd 4:45 PM", "Food", "KSH 5,000", "Creative Consillium " +
                "Limited", "Mpesa"));

    }


    /**
     * TimePickerDialog.OnTimeSetListener to listen to the time set from the TimePickerDialog.
     *
     * Implemented In:
     *          - this.codeToShowTimePickerDialog();
     */
    private TimePickerDialog.OnTimeSetListener otslAddTransactionTime = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            String sTimeSelected = hourOfDay + ":" + minute;
            edSpentTime.setText(sTimeSelected);

        }

    };

    /**
     * MonthPickerDialog.OnDateSetListener interface for handling call backs from the MonthPickerDialog on selected dates.
     *
     * Implemented In:
     *          - this.codeToSetUpMonthYearPicker();
     */
    private MonthPickerDialog.OnDateSetListener odslMonthYearPicker = new MonthPickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(int selectedMonth, int selectedYear) {

            Calendar calMonthSelected = new GregorianCalendar(Locale.getDefault());
            calMonthSelected.set(Calendar.DAY_OF_MONTH, 1);
            calMonthSelected.set(Calendar.MONTH, selectedMonth);
            Date dateMonthSelected = calMonthSelected.getTime();

            SimpleDateFormat sdfTransactionMonth = new SimpleDateFormat("MMMM", Locale.getDefault());
            String sMonthSelected = sdfTransactionMonth.format(dateMonthSelected);
            String sTransactionMonthYear = sMonthSelected + " " + selectedYear;

            txtTransactionMonthYear.setText(sTransactionMonthYear);

            AdapterRVTransactionsDate clsAdapterTransactionsDate = new AdapterRVTransactionsDate(coxContext, codeToGetDatesInSelectedMonthYear(selectedMonth, selectedYear));
            rvTransactionsDate.setAdapter(clsAdapterTransactionsDate);

        }

    };

    /**
     * View.OnClickListener interface for handling clicks on this fragment main layout.
     *
     * Implemented In:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkTransactions = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.btnTransactionsAddSpent) {

                if (bsbAddTransactionBottomSheet.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bsbAddTransactionBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                }  else if (view.getId() == R.id.conslayTransactionsSpentMonthYear) {
                    codeToSetUpMonthYearPicker();
                }

            }

        }

    };

    /**
     * View.OnClickListener interface for handling clicks on this fragment Bottom Sheet layout.
     *
     * Implemented In:
     *          - this.initializeVariablesAndUIObjectsInAddSpentTransactionBottomSheet();
     */
    private View.OnClickListener clkAddSpentTransaction = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.imgvAddTransactionSpentClose:

                    if (bsbAddTransactionBottomSheet.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
                        bsbAddTransactionBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }

                    break;

                case R.id.edAddTransactionSpentTime:
                    codeToShowTimePickerDialog();
                    break;

                case R.id.btnAddTransactionSpentSave:

                    //  TODO: Save Transaction

                    if (bsbAddTransactionBottomSheet.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
                        bsbAddTransactionBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }

                    break;

            }

        }

    };

    /**
     * View.OnClickListener interface for handling clicks on this fragment Bottom Sheet layout.
     *
     * Implemented In:
     *          - this.initializeVariablesAndUIObjectsInTransactionSpentAddToBudgetBottomSheet();
     */
    private View.OnClickListener clkTransactionsSpent = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.imgvTransactionSpentAddToBudgetClose:

                    if (bsbTransactionAddToBudgetBottomSheet.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
                        bsbTransactionAddToBudgetBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }

                    break;

                case R.id.btnTransactionSpentAddToBudgetSave:

                    //  TODO: Save Transaction

                    if (bsbTransactionAddToBudgetBottomSheet.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
                        bsbTransactionAddToBudgetBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }

                    break;

            }

        }

    };

}