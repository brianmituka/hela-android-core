package com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.BudgetUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FormatUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.TransactionsUtil;
import com.creativeconsillium.drumsforafrica.helaapp.R;

public class FragmentHome extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragHome = inflater.inflate(R.layout.fragment_home, container, false);
        initializeVariablesAndUIObjects(vFragHome);
        TransactionsUtil.getTotalForCurrentMonthAndYear(vFragHome);

        return vFragHome;
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
        TextView currentDate = (TextView) fragmentLayout.findViewById(R.id.currentMonthYear);
        TextView spentMonthYear = (TextView) fragmentLayout.findViewById(R.id.spentMonthYear);
        TextView currentYear = (TextView) fragmentLayout.findViewById(R.id.currentYear);
        TextView budgetMonth = (TextView) fragmentLayout.findViewById(R.id.textDate);
        TextView BudgetCount = (TextView) fragmentLayout.findViewById(R.id.textView37);
        String budgetCountString = BudgetUtils.GetUserBudgetCount() + " Categories";
        String dateString = FormatUtils.getCurrentMonth() + " " + FormatUtils.getCurrentYear();
        String currentYearString = FormatUtils.getCurrentYear();
        currentDate.setText(dateString);
        currentYear.setText(currentYearString);
        spentMonthYear.setText(dateString);
        budgetMonth.setText(dateString);
        BudgetCount.setText(budgetCountString);

        CardView cardSummary = fragmentLayout.findViewById(R.id.cardHomeSummary);
        cardSummary.setOnClickListener(clkHomeSummary);

    }


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkHomeSummary = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(FragmentHome.this).navigate(R.id.nav_frag_home_summary);

        }

    };


}