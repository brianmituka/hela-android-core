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
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.ArrayList;

/**
 * Fragment class for Home Summary.
 *
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
     *
     * Called In:
     *          - (Override) this.onCreate();
     *
     * @param fragmentLayout                                    (View)
     */
    private void initializeVariablesAndUIObjects(@NonNull View fragmentLayout) {

        ImageView imgvBack = (ImageView) fragmentLayout.findViewById(R.id.imgvSettingsProfileBackArrow);
        imgvBack.setOnClickListener(clkHomeSummary);

        AdapterRVHomeSummary clsAdapterHomeSummary = new AdapterRVHomeSummary(getContext(), codeToPrepareHomeSummaryDummyData());

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

    /**
     * Method to prepare dummy data for the purpose of testing.
     *
     * Called In:
     *          - this.initializeVariablesAndUIObjects();
     *
     * @return arylHomeSummary                                          (ArrayList<ModelHomeSummary>)
     */
    private ArrayList<ModelHomeSummary> codeToPrepareHomeSummaryDummyData() {

        ModelHomeSummaryBody clsModelHomeSumarryBody0 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody0.setsMonthName("JAN");
        clsModelHomeSumarryBody0.setdAmountReceived(10000);
        clsModelHomeSumarryBody0.setdAmountSpent(2000);

        ModelHomeSummaryBody clsModelHomeSumarryBody1 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody1.setsMonthName("FEB");
        clsModelHomeSumarryBody1.setdAmountReceived(10000);
        clsModelHomeSumarryBody1.setdAmountSpent(2000);

        ModelHomeSummaryBody clsModelHomeSumarryBody2 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody2.setsMonthName("MAR");
        clsModelHomeSumarryBody2.setdAmountReceived(10000);
        clsModelHomeSumarryBody2.setdAmountSpent(2000);

        ModelHomeSummaryBody clsModelHomeSumarryBody3 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody3.setsMonthName("APR");
        clsModelHomeSumarryBody3.setdAmountReceived(100000);
        clsModelHomeSumarryBody3.setdAmountSpent(20000);

        ModelHomeSummaryBody clsModelHomeSumarryBody4 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody4.setsMonthName("MAY");
        clsModelHomeSumarryBody4.setdAmountReceived(20000);
        clsModelHomeSumarryBody4.setdAmountSpent(3000);

        ModelHomeSummaryBody clsModelHomeSumarryBody5 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody5.setsMonthName("JUN");
        clsModelHomeSumarryBody5.setdAmountReceived(15000);
        clsModelHomeSumarryBody5.setdAmountSpent(2500);

        ModelHomeSummaryBody clsModelHomeSumarryBody6 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody6.setsMonthName("JUL");
        clsModelHomeSumarryBody6.setdAmountReceived(30000);
        clsModelHomeSumarryBody6.setdAmountSpent(3000);

        ModelHomeSummaryBody clsModelHomeSumarryBody7 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody7.setsMonthName("AUG");
        clsModelHomeSumarryBody7.setdAmountReceived(500000);
        clsModelHomeSumarryBody7.setdAmountSpent(40000);

        ModelHomeSummaryBody clsModelHomeSumarryBody8 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody8.setsMonthName("SEP");
        clsModelHomeSumarryBody8.setdAmountReceived(2000);
        clsModelHomeSumarryBody8.setdAmountSpent(100);

        ModelHomeSummaryBody clsModelHomeSumarryBody9 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody9.setsMonthName("OCT");
        clsModelHomeSumarryBody9.setdAmountReceived(10000);
        clsModelHomeSumarryBody9.setdAmountSpent(6000);

        ModelHomeSummaryBody clsModelHomeSumarryBody10 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody10.setsMonthName("NOV");
        clsModelHomeSumarryBody10.setdAmountReceived(10000);
        clsModelHomeSumarryBody10.setdAmountSpent(2000);

        ModelHomeSummaryBody clsModelHomeSumarryBody11 = new ModelHomeSummaryBody();
        clsModelHomeSumarryBody11.setsMonthName("DEC");
        clsModelHomeSumarryBody11.setdAmountReceived(0);
        clsModelHomeSumarryBody11.setdAmountSpent(0);

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
        clsModelHomeSummary2019.setsYear("2019");
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
     *
     * Called In:
     *          - (Override) this.clkSettingsProfile.onClick();
     */
    private void codeToNavigateBack() {

        NavHostFragment.findNavController(FragHomeSummary.this).navigateUp();

    }


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented In:
     *          - this.initializeVariablesAndUIObjects();
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
