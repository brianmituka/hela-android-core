package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelTransactionsSpent;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.List;

public class AdapterRVTransactionsSpent extends RecyclerView.Adapter<AdapterRVTransactionsSpent.RVTransactionsSpentViewHolder> {


    private List<ModelTransactionsSpent> lsTransactionsSpent;


    public AdapterRVTransactionsSpent(@NonNull Context context, @NonNull List<ModelTransactionsSpent> listTransactionsSpent) {

        this.lsTransactionsSpent = listTransactionsSpent;

    }


    @NonNull
    @Override
    public RVTransactionsSpentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_transactions_spent, parent, false);

        return new RVTransactionsSpentViewHolder(vItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RVTransactionsSpentViewHolder holder, int position) {

        ModelTransactionsSpent modelTransactionsSpent = lsTransactionsSpent.get(position);
        holder.transactionSpentDate.setText(modelTransactionsSpent.getTransactionSpentDate());
        holder.transactionSpentBudget.setText(modelTransactionsSpent.getTransactionSpentBudget());
        holder.transactionSpentAmount.setText(modelTransactionsSpent.getTransactionSpentAmount());
        holder.transactionSpentPerson.setText(modelTransactionsSpent.getTransactionSpentPerson());
        holder.transactionSpentType.setText(modelTransactionsSpent.getTransactionSpentType());

    }

    @Override
    public int getItemCount() {
        return lsTransactionsSpent.size();
    }



    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter Transactions Spent.
     */
    static class RVTransactionsSpentViewHolder extends RecyclerView.ViewHolder {


        private TextView transactionSpentDate, transactionSpentBudget, transactionSpentAmount, transactionSpentPerson,
                transactionSpentType;


        private RVTransactionsSpentViewHolder(@NonNull View itemView) {
            super(itemView);

            transactionSpentDate = (TextView) itemView.findViewById(R.id.tDate);
            transactionSpentBudget = (Button) itemView.findViewById(R.id.tBudgetBtnLabel);
            transactionSpentAmount = (TextView) itemView.findViewById(R.id.tAmount);
            transactionSpentPerson = (TextView) itemView.findViewById(R.id.tPerson);
            transactionSpentType = (TextView) itemView.findViewById(R.id.tType);

        }




    }

}