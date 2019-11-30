package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelHomeSummaryBody;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * RecyclerView.Adapter class for Home Summary Body RecyclerView.
 *
 * Created by Edward N. Ndukui,
 * on Thursday, 31st/October/2019,
 * at 6:56PM.
 */
public class AdapterRVHomeSummaryBody extends RecyclerView.Adapter<AdapterRVHomeSummaryBody.RVHomeSummaryBodyViewHolder> {

    private Context coxContext;

    private ArrayList<ModelHomeSummaryBody> arylHomeSummaryBodyItems;


    public AdapterRVHomeSummaryBody(Context context, ArrayList<ModelHomeSummaryBody> arrayListHomeSummaryBodyItems) {

        this.coxContext = context;

        this.arylHomeSummaryBodyItems = arrayListHomeSummaryBodyItems;

    }


    @Override
    public int getItemCount() {

        Log.e("AdapterRVHomeSummaryBod", "getItemCount() - arylHomeSummaryBodyItems.size(): " + arylHomeSummaryBodyItems.size());    //  TODO: For Testing ONLY

        return arylHomeSummaryBodyItems.size();
    }

    @NonNull
    @Override
    public RVHomeSummaryBodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (new RVHomeSummaryBodyViewHolder(LayoutInflater.from(coxContext).inflate(R.layout.rowlayout_rv_home_summary_body, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RVHomeSummaryBodyViewHolder holder, int position) {

        String sMonthName = arylHomeSummaryBodyItems.get(position).getsMonthName();
        BigDecimal dAmountReceived = arylHomeSummaryBodyItems.get(position).getdAmountReceived();
        BigDecimal dAmountSpent = arylHomeSummaryBodyItems.get(position).getdAmountSpent();

        String sAmountReceived = "KSH " + dAmountReceived;
        String sAmountSpent = "KSH " + dAmountSpent;

        holder.txtMonthName.setText(sMonthName);
        holder.txtAmountReceived.setText(sAmountReceived);
        holder.txtAmountSpent.setText(sAmountSpent);

        Log.e("AdapterRVHomeSummaryBod", "onBindViewHolder() - sMonthName: " + sMonthName + "   position: " + position);    //  TODO: For Testing ONLY

    }


    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter for Home Summary Body RecyclerView.
     */
    protected static class RVHomeSummaryBodyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMonthName;
        private TextView txtAmountReceived;
        private TextView txtAmountSpent;


        protected RVHomeSummaryBodyViewHolder(View itemView) {
            super(itemView);

            txtMonthName = (TextView) itemView.findViewById(R.id.txtHomeSummaryMonthName);
            txtAmountReceived = (TextView) itemView.findViewById(R.id.txtHomeSummaryAmountReceived);
            txtAmountSpent = (TextView) itemView.findViewById(R.id.txtHomeSummaryAmountSpent);

        }

    }

}
