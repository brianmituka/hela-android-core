package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelHomeSummaryDetails;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.List;

public class AdapterRVHomeSummaryDetails extends RecyclerView.Adapter<AdapterRVHomeSummaryDetails.RVHomeSummaryViewHolder> {

    private List<ModelHomeSummaryDetails> lsHomeSummaryDetails;




    public AdapterRVHomeSummaryDetails(@NonNull Context context, @NonNull List<ModelHomeSummaryDetails> listHomeSummaryDetails) {

        this.lsHomeSummaryDetails = listHomeSummaryDetails;

    }




    @NonNull
    @Override
    public AdapterRVHomeSummaryDetails.RVHomeSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_home_summary_main, parent, false);

        return new AdapterRVHomeSummaryDetails.RVHomeSummaryViewHolder(vItemView);
    }




    @Override
    public void onBindViewHolder(@NonNull AdapterRVHomeSummaryDetails.RVHomeSummaryViewHolder holder, int position) {

        ModelHomeSummaryDetails modelHomeSummaryDetails = lsHomeSummaryDetails.get(position);
        holder.homeSummaryDetailMonth.setText(modelHomeSummaryDetails.getHomeSummaryDetailMonth());
        holder.homeSummaryDetailReceived.setText(modelHomeSummaryDetails.getHomeSummaryDetailReceived());
        holder.homeSummaryDetailSpent.setText(modelHomeSummaryDetails.getHomeSummaryDetailSpent());

    }




    @Override
    public int getItemCount() {
        return lsHomeSummaryDetails.size();
    }





    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter Transactions Received.
     */
    static class RVHomeSummaryViewHolder extends RecyclerView.ViewHolder {


        private TextView homeSummaryDetailMonth, homeSummaryDetailReceived, homeSummaryDetailSpent;


        private RVHomeSummaryViewHolder(@NonNull View itemView) {
            super(itemView);

            homeSummaryDetailMonth = (TextView) itemView.findViewById(R.id.homeSummaryDetailMonth);
            homeSummaryDetailReceived = (TextView) itemView.findViewById(R.id.homeSummaryDetailReceived);
            homeSummaryDetailSpent = (TextView) itemView.findViewById(R.id.homeSummaryDetailSpent);

        }




    }




}