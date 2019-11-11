package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Interface.InterfaceBudgets;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelBudgets;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.List;

public class AdapterRVBudgets extends RecyclerView.Adapter<AdapterRVBudgets.RVBudgetsViewHolder> {

    private InterfaceBudgets interBudgets;

    private List<ModelBudgets> lsBudgets;


    public AdapterRVBudgets(@NonNull Context context, @NonNull List<ModelBudgets> listBudgets) {

        this.lsBudgets = listBudgets;

        try {
            interBudgets = (InterfaceBudgets) context;
        } catch (ClassCastException ccex) {
            throw new ClassCastException("InterfaceBudgets MUST be Implemented!");
        }

    }


    @Override
    public int getItemCount() {
        return lsBudgets.size();
    }

    @NonNull
    @Override
    public AdapterRVBudgets.RVBudgetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_budgets, parent, false);

        return new AdapterRVBudgets.RVBudgetsViewHolder(vItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRVBudgets.RVBudgetsViewHolder holder, int position) {

        ModelBudgets modelBudgets = lsBudgets.get(position);
        holder.budgetCategory.setText(modelBudgets.getBudgetCategory());
        holder.budgetNumber.setText(modelBudgets.getBudgetNumber());
        holder.budgetSpentAmount.setText(modelBudgets.getBudgetSpentAmount());
        holder.budgetRemainingAmount.setText(modelBudgets.getBudgetRemainingAmount());

        holder.itemView.setOnClickListener(clkRVBudgets);

    }


    /**
     * View.OnClickListener interface for handling clicks on item views.
     *
     * Implemented In:
     *          - (Override) this.onBindViewHolder();
     */
    private View.OnClickListener clkRVBudgets = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            if (interBudgets != null) {
                interBudgets.codeToLaunchBudgetDetail();
            }

        }

    };



    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter Transactions Received.
     */
    static class RVBudgetsViewHolder extends RecyclerView.ViewHolder {

        private TextView budgetCategory, budgetNumber, budgetSpentAmount, budgetRemainingAmount;


        private RVBudgetsViewHolder(@NonNull View itemView) {
            super(itemView);

            budgetCategory = (TextView) itemView.findViewById(R.id.budgetCategory);
            budgetNumber = (TextView) itemView.findViewById(R.id.budgetNumber);
            budgetSpentAmount = (TextView) itemView.findViewById(R.id.budgetSpentAmount);
            budgetRemainingAmount = (TextView) itemView.findViewById(R.id.budgetRemainingAmount);

        }

    }
}