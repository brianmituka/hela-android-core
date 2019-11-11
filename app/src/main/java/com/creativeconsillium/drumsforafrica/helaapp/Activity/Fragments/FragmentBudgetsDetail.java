package com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments;

import android.content.Context;
import android.os.Bundle;
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

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter.AdapterRVBudgetsDetail;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelBudgetsDetail;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentBudgetsDetail extends Fragment {


    private Context coxContext;

    private List<ModelBudgetsDetail> lsBudgetsDetail;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragBudgets = inflater.inflate(R.layout.fragment_budgets_detail, container, false);

        initializeVariablesAndUIObjects(vFragBudgets);

        return vFragBudgets;
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

        AppCompatActivity acactParentActivity = (AppCompatActivity) getActivity();
        if (acactParentActivity != null) {

            ActionBar abActivityActionBar = acactParentActivity.getSupportActionBar();
            if (abActivityActionBar != null) {
                abActivityActionBar.setTitle("Food Budget");
                abActivityActionBar.setDisplayHomeAsUpEnabled(false);
            }

        }

        ImageView imgvBack = (ImageView) fragmentLayout.findViewById(R.id.imgvBudgetsDetailBackArrow);
        imgvBack.setOnClickListener(clkBudgetsDetail);

        lsBudgetsDetail = new ArrayList<>();

        AdapterRVBudgetsDetail clsAdapterBudgetsDetail = new AdapterRVBudgetsDetail(coxContext, lsBudgetsDetail);

        RecyclerView rvBudgets = (RecyclerView) fragmentLayout.findViewById(R.id.recyclerViewBudgetsDetail);
        RecyclerView.LayoutManager rvlmLayoutManager = new LinearLayoutManager(getActivity());
        rvBudgets.setLayoutManager(rvlmLayoutManager);
        rvBudgets.setAdapter(clsAdapterBudgetsDetail);

        codeToPrepareBudgetsdetailData();

    }

    private void codeToPrepareBudgetsdetailData() {

        lsBudgetsDetail.add(new ModelBudgetsDetail("Today 4:45 PM", "Food", "KSH 5,000", "Michael Mutethia Mutua", "Mpesa"));
        lsBudgetsDetail.add(new ModelBudgetsDetail("Mon 9th 4:45 PM", "Food", "KSH 2,000", "Savings", "Cash"));

    }


    /**
     * Method to handle back/up navigation from this Fragment.
     *
     * Called In:
     *          - (Override) this.clkSettingsProfile.onClick();
     */
    private void codeToNavigateBack() {

        NavHostFragment.findNavController(FragmentBudgetsDetail.this).navigateUp();

    }


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented In:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkBudgetsDetail = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.imgvBudgetsDetailBackArrow:
                    codeToNavigateBack();
                    break;

            }

        }

    };


}