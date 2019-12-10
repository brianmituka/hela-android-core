package com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter.AdapterRVHomeSummary;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelHomeSummary;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelHomeSummaryBody;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FormatUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.OnGetDataListener;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.TransactionsUtil;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.firebase.database.DataSnapshot;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Fragment class for Home Summary.
 * <p>
 * Created by Edward N. Ndukui,
 * on Thursday, 31st/October/2019,
 * at 6:16PM.
 */
public class FragHomeSummary extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragHomeSummary = inflater.inflate(R.layout.frag_home_summary, container, false);

        initializeVariablesAndUIObjects(vFragHomeSummary);

        return vFragHomeSummary;
    }


    /**
     * Method to declare and intialize class variables and UI Objects referenced in this Fragment.
     * <p>
     * Called In:
     * - (Override) this.onCreate();
     *
     * @param fragmentLayout (View)
     */
    private void initializeVariablesAndUIObjects(@NonNull final View fragmentLayout) {

        ImageView imgvBack = (ImageView) fragmentLayout.findViewById(R.id.imgvSettingsProfileBackArrow);
        imgvBack.setOnClickListener(clkHomeSummary);

        TransactionsUtil.readTransactionByMonth(new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                AdapterRVHomeSummary clsAdapterHomeSummary = new AdapterRVHomeSummary(getContext(), codeToPrepareHomeSummaryDummyData(dataSnapshot));

                AppCompatActivity acactParentActivity = (AppCompatActivity) getActivity();
                if (acactParentActivity != null) {

                    ActionBar abActivityActionBar = acactParentActivity.getSupportActionBar();
                    if (abActivityActionBar != null) {
                        abActivityActionBar.setTitle(getString(R.string.titleSummary));
                        abActivityActionBar.setDisplayHomeAsUpEnabled(false);
                    }

                }

                RecyclerView rvHomeSummary = (RecyclerView) fragmentLayout.findViewById(R.id.rvHomeSummary);
                RecyclerView.LayoutManager rvlmLayoutManager = new LinearLayoutManager(getContext());
                rvHomeSummary.setLayoutManager(rvlmLayoutManager);
                rvHomeSummary.setHasFixedSize(true);
                rvHomeSummary.setAdapter(clsAdapterHomeSummary);

            }

            @Override
            public void onStart() {
//                TODO Add progress diglot here
            }

            @Override
            public void onFailure() {
//              TODO Remove progress dialog here
            }
        });


    }

    private void setSummaryBodyFields(String month, ModelHomeSummaryBody summaryBody, DataSnapshot spentSnapshot, DataSnapshot receivedSnapshot) {
        summaryBody.setsMonthName(month);
        if (receivedSnapshot.hasChild(month)) {
            Object receivedTotal = receivedSnapshot.child(month).getValue();
            if (receivedTotal != null) {
                summaryBody.setdAmountReceived(FormatUtils.formatMpesaAmount(receivedTotal));
            } else {
                summaryBody.setdAmountReceived(new BigDecimal(0.00));
            }
        }else {
            summaryBody.setdAmountReceived(new BigDecimal(0.00));
        }

        if (spentSnapshot.hasChild(month)) {
            Object spentTotal = spentSnapshot.child(month).getValue();
            if (spentTotal != null) {
                summaryBody.setdAmountSpent(FormatUtils.formatMpesaAmount(spentTotal));
            } else {
                summaryBody.setdAmountSpent(new BigDecimal(0.00));
            }
        }else {
            summaryBody.setdAmountSpent(new BigDecimal(0.00));
        }

    }

    /**
     * Method to prepare dummy data for the purpose of testing.
     * <p>
     * Called In:
     * - this.initializeVariablesAndUIObjects();
     *
     * @return arylHomeSummary                                          (ArrayList<ModelHomeSummary>)
     */
    private ArrayList<ModelHomeSummary> codeToPrepareHomeSummaryDummyData(DataSnapshot dataSnapshot) {

        DataSnapshot spentSnapshot = dataSnapshot.child("allmonthsSpent");
        DataSnapshot receivedSnapshot = dataSnapshot.child("allmonthsReceived");

        ModelHomeSummaryBody clsModelHomeSumarryBody0 = new ModelHomeSummaryBody();
        setSummaryBodyFields("JAN", clsModelHomeSumarryBody0, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody1 = new ModelHomeSummaryBody();
        setSummaryBodyFields("FEB", clsModelHomeSumarryBody1, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody2 = new ModelHomeSummaryBody();
        setSummaryBodyFields("MAR", clsModelHomeSumarryBody2, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody3 = new ModelHomeSummaryBody();
        setSummaryBodyFields("APR", clsModelHomeSumarryBody3, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody4 = new ModelHomeSummaryBody();
        setSummaryBodyFields("MAY", clsModelHomeSumarryBody4, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody5 = new ModelHomeSummaryBody();
        setSummaryBodyFields("JUN", clsModelHomeSumarryBody5, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody6 = new ModelHomeSummaryBody();
        setSummaryBodyFields("JUL", clsModelHomeSumarryBody6, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody7 = new ModelHomeSummaryBody();
        setSummaryBodyFields("AUG", clsModelHomeSumarryBody7, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody8 = new ModelHomeSummaryBody();
        setSummaryBodyFields("SEP", clsModelHomeSumarryBody8, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody9 = new ModelHomeSummaryBody();
        setSummaryBodyFields("OCT", clsModelHomeSumarryBody9, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody10 = new ModelHomeSummaryBody();
        setSummaryBodyFields("NOV", clsModelHomeSumarryBody10, spentSnapshot, receivedSnapshot);

        ModelHomeSummaryBody clsModelHomeSumarryBody11 = new ModelHomeSummaryBody();
        setSummaryBodyFields("DEC", clsModelHomeSumarryBody11, spentSnapshot, receivedSnapshot);


        ArrayList<ModelHomeSummaryBody> arylSummaryBodies2019 = new ArrayList<>();
        arylSummaryBodies2019.add(clsModelHomeSumarryBody0);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody1);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody2);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody3);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody4);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody5);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody6);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody7);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody8);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody9);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody10);
        arylSummaryBodies2019.add(clsModelHomeSumarryBody11);

        ArrayList<ModelHomeSummaryBody> arylSummaryBodies2018 = new ArrayList<>();
        arylSummaryBodies2018.add(clsModelHomeSumarryBody0);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody1);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody2);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody3);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody4);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody5);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody6);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody7);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody8);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody9);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody10);
        arylSummaryBodies2018.add(clsModelHomeSumarryBody11);

        ModelHomeSummary clsModelHomeSummary2019 = new ModelHomeSummary();
        clsModelHomeSummary2019.setsYear(FormatUtils.getCurrentYear());
        clsModelHomeSummary2019.setdAmountReceived(500000);
        clsModelHomeSummary2019.setdAmountSpent(20000);
        clsModelHomeSummary2019.setArylHomeSummaryBodyItems(arylSummaryBodies2019);

        ModelHomeSummary clsModelHomeSummary2018 = new ModelHomeSummary();
        clsModelHomeSummary2018.setsYear("2018");
        clsModelHomeSummary2018.setdAmountReceived(523000);
        clsModelHomeSummary2018.setdAmountSpent(25000);
        clsModelHomeSummary2018.setArylHomeSummaryBodyItems(arylSummaryBodies2018);

        ArrayList<ModelHomeSummary> arylHomeSummary = new ArrayList<>();
        arylHomeSummary.add(clsModelHomeSummary2019);
        arylHomeSummary.add(clsModelHomeSummary2018);

        Log.e("FragHomeSummary", "codeToPrepareHomeSummaryDummyData() - arylSummaryBodies2019.size(): " + arylSummaryBodies2019.size() + "      arylHomeSummary.size(): " + arylHomeSummary.size());    //  TODO: For Testing ONLY

        return arylHomeSummary;
    }


    /**
     * Method to handle back/up navigation from this Fragment.
     * <p>
     * Called In:
     * - (Override) this.clkSettingsProfile.onClick();
     */
    private void codeToNavigateBack() {

        NavHostFragment.findNavController(FragHomeSummary.this).navigateUp();

    }


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     * <p>
     * Implemented In:
     * - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkHomeSummary = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.imgvSettingsProfileBackArrow:
                    codeToNavigateBack();
                    break;

            }

        }

    };

}
