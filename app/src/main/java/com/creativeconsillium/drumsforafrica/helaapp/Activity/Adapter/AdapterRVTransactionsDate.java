package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelTransactionsDate;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.List;

public class AdapterRVTransactionsDate extends RecyclerView.Adapter<AdapterRVTransactionsDate.RVTransactionDatesViewHolder> {

    private View vPreviouslySelectedItem;

    private TextView txtPreviouslySelectedTransactionDate;

    private Context coxContext;

    private List<ModelTransactionsDate> lsTransactionsDate;


    public AdapterRVTransactionsDate(@NonNull Context context, @NonNull List<ModelTransactionsDate> listTransactionsDate) {

        this.coxContext = context;

        this.lsTransactionsDate = listTransactionsDate;

    }


    @Override
    public int getItemCount() {
        return lsTransactionsDate.size();
    }

    @NonNull
    @Override
    public RVTransactionDatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_transactions_dates, parent, false);

        return (new RVTransactionDatesViewHolder(itemView, coxContext, lsTransactionsDate));
    }

    @Override
    public void onBindViewHolder(final RVTransactionDatesViewHolder holder, int position) {

        ModelTransactionsDate clsModelTransactionsDate = lsTransactionsDate.get(position);
        String sTransactionDate = clsModelTransactionsDate.getTransactionDate();
        boolean boolIsCurrentDate = clsModelTransactionsDate.isBoolIsCurrentDate();

        holder.txtTransactionDate.setText(sTransactionDate);

        if (boolIsCurrentDate) {

            holder.itemView.setBackgroundResource(R.drawable.style_bg_blue_primary_rounded);
            holder.txtTransactionDate.setTextColor(ContextCompat.getColor(coxContext, android.R.color.white));

            vPreviouslySelectedItem = holder.itemView;
            txtPreviouslySelectedTransactionDate = holder.txtTransactionDate;

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //  TODO - BUG: Views are recycled, so it's bloody selecting the double factor of item selected!

                if (vPreviouslySelectedItem != null) {

                    vPreviouslySelectedItem.setBackgroundColor(ContextCompat.getColor(coxContext, android.R.color.transparent));
                    txtPreviouslySelectedTransactionDate.setTextColor(ContextCompat.getColor(coxContext, R.color.colorTextBlack));

                }

                holder.itemView.setBackgroundResource(R.drawable.style_bg_blue_primary_rounded);
                holder.txtTransactionDate.setTextColor(ContextCompat.getColor(coxContext, android.R.color.white));

                vPreviouslySelectedItem = holder.itemView;
                txtPreviouslySelectedTransactionDate = holder.txtTransactionDate;

            }

        });

    }



    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter Transactions Received.
     */
    static class RVTransactionDatesViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTransactionDate;

        private Context coxContext;

        private List<ModelTransactionsDate> lsTransactionDates;


        private RVTransactionDatesViewHolder(@NonNull final View itemView, Context context, List<ModelTransactionsDate> listTransactionDates) {
            super(itemView);

            this.coxContext = context;

            this.lsTransactionDates = listTransactionDates;

            txtTransactionDate = (TextView) itemView.findViewById(R.id.transaction_date);

        }

    }

}