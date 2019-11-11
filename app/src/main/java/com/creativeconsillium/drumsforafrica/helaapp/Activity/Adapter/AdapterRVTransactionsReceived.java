package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelTransactionsReceived;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.List;

public class AdapterRVTransactionsReceived extends RecyclerView.Adapter<AdapterRVTransactionsReceived.RVTransactionsReceivedViewHolder> {


    private List<ModelTransactionsReceived> lsTransactionsReceived;



    public AdapterRVTransactionsReceived(@NonNull Context context, @NonNull List<ModelTransactionsReceived> listTransactionsReceived) {

        this.lsTransactionsReceived = listTransactionsReceived;

    }



    @NonNull
    @Override
    public RVTransactionsReceivedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_transactions_received, parent, false);

        return new RVTransactionsReceivedViewHolder(vItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RVTransactionsReceivedViewHolder holder, int position) {

        ModelTransactionsReceived modelTransactionsReceived = lsTransactionsReceived.get(position);
        holder.transactionDate.setText(modelTransactionsReceived.getTransactionDate());
        holder.transactionAmount.setText(modelTransactionsReceived.getTransactionAmount());
        holder.transactionPerson.setText(modelTransactionsReceived.getTransactionPerson());
        holder.transactionType.setText(modelTransactionsReceived.getTransactionType());

    }

    @Override
    public int getItemCount() {
        return lsTransactionsReceived.size();
    }



    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter Transactions Received.
     */
    static class RVTransactionsReceivedViewHolder extends RecyclerView.ViewHolder {


        private TextView transactionDate, transactionAmount, transactionPerson, transactionType;


        private RVTransactionsReceivedViewHolder(@NonNull View itemView) {
            super(itemView);

            transactionDate = (TextView) itemView.findViewById(R.id.tDate);
            transactionAmount = (TextView) itemView.findViewById(R.id.tAmount);
            transactionPerson = (TextView) itemView.findViewById(R.id.tPerson);
            transactionType = (TextView) itemView.findViewById(R.id.tType);

        }




    }

}