package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelHomeSummaryDetails;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelHomeSummaryMain;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.List;

public class AdapterRVHomeSummaryMain extends RecyclerView.Adapter<AdapterRVHomeSummaryMain.RVHomeSummaryViewHolder> {

    private List<ModelHomeSummaryMain> lsHomeSummaryMain;
    private List<ModelHomeSummaryDetails> lsHomeSummaryDetails;

    private Context coxContext;



    public AdapterRVHomeSummaryMain(@NonNull Context context, @NonNull List<ModelHomeSummaryMain> listHomeSummary) {

        this.lsHomeSummaryMain = listHomeSummary;

    }


    @NonNull
    @Override
    public AdapterRVHomeSummaryMain.RVHomeSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_home_summary_main, parent, false);
        return new AdapterRVHomeSummaryMain.RVHomeSummaryViewHolder(vItemView);
    }




    @Override
    public void onBindViewHolder(@NonNull AdapterRVHomeSummaryMain.RVHomeSummaryViewHolder holder, int position) {


        // Show list of years

        ModelHomeSummaryMain modelHomeSummaryMain = lsHomeSummaryMain.get(position);
        holder.homeSummaryYear.setText(modelHomeSummaryMain.getHomeSummaryYear());
        holder.homeSummaryAction.setText(modelHomeSummaryMain.getHomeSummaryAction());
        holder.homeSummaryReceivedAmount.setText(modelHomeSummaryMain.getHomeSummaryReceivedAmount());
        holder.homeSummarySpentAmount.setText(modelHomeSummaryMain.getHomeSummarySpentAmount());



        //bind the inner recycler view to an adapter



    }

    @Override
    public int getItemCount() {
        return lsHomeSummaryMain.size();
    }



    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter Transactions Received.
     */
    static class RVHomeSummaryViewHolder extends RecyclerView.ViewHolder {


        private TextView homeSummaryYear, homeSummaryAction, homeSummaryReceivedAmount, homeSummarySpentAmount;


        private RVHomeSummaryViewHolder(@NonNull View itemView) {
            super(itemView);

            homeSummaryYear = (TextView) itemView.findViewById(R.id.homeSummaryYear);
            homeSummaryAction = (TextView) itemView.findViewById(R.id.homeSummaryAction);
            homeSummaryReceivedAmount = (TextView) itemView.findViewById(R.id.homeSummaryReceivedAmount);
            homeSummarySpentAmount = (TextView) itemView.findViewById(R.id.homeSummarySpentAmount);

        }




    }
}