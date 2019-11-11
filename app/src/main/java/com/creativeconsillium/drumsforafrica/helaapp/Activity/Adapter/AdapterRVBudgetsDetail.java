package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelBudgetsDetail;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.List;

public class AdapterRVBudgetsDetail extends RecyclerView.Adapter<AdapterRVBudgetsDetail.RVBudgetsDetailViewHolder> {


    private List<ModelBudgetsDetail> lsBudgetsDetail;


    public AdapterRVBudgetsDetail(@NonNull Context context, @NonNull List<ModelBudgetsDetail> listBudgetsDetail) {

        this.lsBudgetsDetail = listBudgetsDetail;

    }


    @NonNull
    @Override
    public RVBudgetsDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_budgets_detail, parent, false);

        return new RVBudgetsDetailViewHolder(vItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RVBudgetsDetailViewHolder holder, int position) {

        ModelBudgetsDetail modelBudgetsDetail = lsBudgetsDetail.get(position);
        holder.transactionSpentDate.setText(modelBudgetsDetail.getTransactionSpentDate());
        holder.transactionSpentBudget.setText(modelBudgetsDetail.getTransactionSpentBudget());
        holder.transactionSpentAmount.setText(modelBudgetsDetail.getTransactionSpentAmount());
        holder.transactionSpentPerson.setText(modelBudgetsDetail.getTransactionSpentPerson());
        holder.transactionSpentType.setText(modelBudgetsDetail.getTransactionSpentType());

    }

    @Override
    public int getItemCount() {
        return lsBudgetsDetail.size();
    }



    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter Transactions Spent.
     */
    static class RVBudgetsDetailViewHolder extends RecyclerView.ViewHolder {


        private TextView transactionSpentDate, transactionSpentBudget, transactionSpentAmount, transactionSpentPerson,
                transactionSpentType;


        private RVBudgetsDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            transactionSpentDate = (TextView) itemView.findViewById(R.id.tDate);
            transactionSpentBudget = (Button) itemView.findViewById(R.id.tBudgetBtnLabel);
            transactionSpentAmount = (TextView) itemView.findViewById(R.id.tAmount);
            transactionSpentPerson = (TextView) itemView.findViewById(R.id.tPerson);
            transactionSpentType = (TextView) itemView.findViewById(R.id.tType);

        }




    }

}