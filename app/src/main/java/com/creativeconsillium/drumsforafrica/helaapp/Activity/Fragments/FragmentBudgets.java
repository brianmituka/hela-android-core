package com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter.AdapterRVBudgets;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelBudgets;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.BudgetUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FormatUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.UiUtils;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class FragmentBudgets extends Fragment {

    private List<ModelBudgets> lsBudgets;

    private BottomSheetBehavior bsbAddBudgetBottomSheet;
    public String TAG = FragmentBudgets.class.getSimpleName();
    EditText edBudgetName;
    EditText edMonthlyLimit;
    Switch swtMonthlyRepeat;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragBudgets = inflater.inflate(R.layout.fragment_budgets, container, false);

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

        lsBudgets = new ArrayList<>();
        //codeToPrepareBudgetsData();
        BudgetUtils.getUserBudgets();
        TextView noBudgets = fragmentLayout.findViewById(R.id.nobudgets);
        if (BudgetUtils.getUserBudgets().isEmpty()){
            noBudgets.setVisibility(View.VISIBLE);
        }
        AdapterRVBudgets clsAdapterBudgets = new AdapterRVBudgets(getContext(), BudgetUtils.getUserBudgets());
        String dateString = FormatUtils.getCurrentMonth() + " " + FormatUtils.getCurrentYear();
        TextView budgetMonthYear = (TextView) fragmentLayout.findViewById(R.id.budgetMonthYear);
        budgetMonthYear.setText(dateString);


        RecyclerView rvBudgets = fragmentLayout.findViewById(R.id.recyclerViewBudgets);
        rvBudgets.setOnClickListener(clkBudgetsDetail);

        Button btnAddBudgets = fragmentLayout.findViewById(R.id.btnBudgetsAddBudget);
        btnAddBudgets.setOnClickListener(clkBudgetsAddBudget);

        rvBudgets = (RecyclerView) fragmentLayout.findViewById(R.id.recyclerViewBudgets);
        RecyclerView.LayoutManager rvlmLayoutManager = new LinearLayoutManager(getActivity());
        rvBudgets.setLayoutManager(rvlmLayoutManager);
        rvBudgets.setAdapter(clsAdapterBudgets);

        View vAddBudgetBottomSheet = fragmentLayout.findViewById(R.id.nsvBudgetsAdd);
        bsbAddBudgetBottomSheet = BottomSheetBehavior.from(vAddBudgetBottomSheet);
        bsbAddBudgetBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bsbAddBudgetBottomSheet.setPeekHeight(0);
        bsbAddBudgetBottomSheet.setHideable(false);

        initializeVariablesAndUIObjectsInAddBudgetBottomSheet(vAddBudgetBottomSheet);

    }

    /**
     * Method to declare and initialize variables and UI Objects referenced in the Add Budget Bottom Sheet.
     *
     * Called In:
     *          - this.initializeVariablesAndUIObjects();
     *
     * @param bottomSheetLayout                                     (View) The Add Budget Bottom Sheet Layout.
     */
    private void initializeVariablesAndUIObjectsInAddBudgetBottomSheet(@NonNull View bottomSheetLayout) {

        ImageView imgvClose = (ImageView) bottomSheetLayout.findViewById(R.id.imgvAddBudgetClose);
        imgvClose.setOnClickListener(clkAddBudget);

         edBudgetName = (EditText) bottomSheetLayout.findViewById(R.id.edAddBudgetName);
         edMonthlyLimit = (EditText) bottomSheetLayout.findViewById(R.id.edAddBudgetMonthlyLimit);

         swtMonthlyRepeat = (Switch) bottomSheetLayout.findViewById(R.id.swtAddBudgetMonthlyRepeat);

        Button btnSaveBudget = (Button) bottomSheetLayout.findViewById(R.id.btnAddBudgetCreateBudget);
        btnSaveBudget.setOnClickListener(clkAddBudget);

    }

    private void codeToPrepareBudgetsData() {

        /**
         * Display the data here
         */

        lsBudgets.add(new ModelBudgets("Food", "3 Transactions", "KSH 10,000", "KSH 3,000"));
        lsBudgets.add(new ModelBudgets("Transport", "10 Transactions", "KSH 8,000", "KSH 2,000"));


    }


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkBudgetsDetail = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(FragmentBudgets.this).navigate(R.id.nav_frag_budgets_detail);

        }

    };

    /**
     * View.OnClickListener interface for handling clicks on views in BottomSheet in Add Budget.
     *
     * Implemented In:
     *          - this.initializeVariablesAndUIObjectsInAddBudgetBottomSheet();
     */
    private View.OnClickListener clkAddBudget = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.imgvAddBudgetClose:

                    bsbAddBudgetBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    break;

                case R.id.btnAddBudgetCreateBudget:
                    //  TODO: Save Budget here.
                    new CreateBudget().execute(edBudgetName.getText().toString(),swtMonthlyRepeat.isChecked(), edMonthlyLimit.getText().toString());

                    edBudgetName.setText("");
                    edMonthlyLimit.setText("");
                    swtMonthlyRepeat.setChecked(false);
                    bsbAddBudgetBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    break;


            }

        }

    };

    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkBudgetsAddBudget = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            bsbAddBudgetBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);

        }

    };

    private class CreateBudget extends AsyncTask<Object, Void, Void>{
        @Override
        protected void onPreExecute() {
            UiUtils.showDialog("Creating Budget", getActivity());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Object... params) {
            String budgetName = (String) params[0];
            String amount = (String) params[2];
            boolean isRecurring = (boolean) params[1];
            String currentMonthYear = FormatUtils.getCurrentYearAndMonth();
            Log.i(TAG, "Name " + budgetName + " Amount " + amount + " IsRePEAT " + isRecurring + " monthYear " + currentMonthYear);
            ModelBudgets budget = new ModelBudgets(budgetName, "1 Transaction","KSH 0", "KSH 0", isRecurring, amount, currentMonthYear);
            BudgetUtils.createAndSaveBudget(budget);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            UiUtils.hideDialog();
            super.onPostExecute(aVoid);
        }
    }

}