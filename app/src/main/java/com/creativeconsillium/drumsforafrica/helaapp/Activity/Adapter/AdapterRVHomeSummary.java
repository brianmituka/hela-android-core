package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelHomeSummary;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelHomeSummaryBody;
import com.creativeconsillium.drumsforafrica.helaapp.R;

import java.util.ArrayList;

/**
 * RecyclerView.Adapter class for Home Summary RecyclerView.
 *
 * Created by Edward N. Ndukui,
 * on Thursday, 31st/October/2019,
 * at 6:51PM.
 */
public class AdapterRVHomeSummary extends RecyclerView.Adapter<AdapterRVHomeSummary.RVHomeSummaryViewHolder> {

    private Context coxContext;

    private ArrayList<ModelHomeSummary> arylHomeSummaryItems;


    public AdapterRVHomeSummary(Context context, ArrayList<ModelHomeSummary> arrayListHomeSummaryItems) {

        this.coxContext = context;

        this.arylHomeSummaryItems = arrayListHomeSummaryItems;

    }


    @Override
    public int getItemCount() {

        Log.e("AdapterRVHomeSummary", "getItemCount() - arylHomeSummaryItems.size(): " + arylHomeSummaryItems.size());    //  TODO: For Testing ONLY

        return arylHomeSummaryItems.size();
    }

    @NonNull
    @Override
    public RVHomeSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (new RVHomeSummaryViewHolder(LayoutInflater.from(coxContext).inflate(R.layout.rowlayout_rv_home_summary_header, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RVHomeSummaryViewHolder holder, int position) {

        String sYear = arylHomeSummaryItems.get(position).getsYear();
        double dAmountReceived = arylHomeSummaryItems.get(position).getdAmountReceived();
        double dAmountSpent = arylHomeSummaryItems.get(position).getdAmountSpent();

        String sAmountReceived = "KSH " + dAmountReceived;
        String sAmountSpent = "KSH " + dAmountSpent;

        holder.txtYear.setText(sYear);
        holder.txtAmountReceived.setText(sAmountReceived);
        holder.txtAmountSpent.setText(sAmountSpent);

        codeToSetupBodyRecyclerView(holder.rvHomeSummaryBody, arylHomeSummaryItems.get(position).getArylHomeSummaryBodyItems());

    }

    /**
     * Method to set up Summary body RecyclerView.
     *
     * Called In:
     *          - (Override) this.onBindViewHolder();
     *
     * @param recyclerViewHomeSummaryBody                       (RecyclerView)
     * @param arrayListHomeSummaryBodyItems                     (ArrayList<ModelHomeSummaryBody>)
     */
    private void codeToSetupBodyRecyclerView(@NonNull RecyclerView recyclerViewHomeSummaryBody, ArrayList<ModelHomeSummaryBody> arrayListHomeSummaryBodyItems) {

        AdapterRVHomeSummaryBody clsAdapterHomeSummaryBody = new AdapterRVHomeSummaryBody(coxContext, arrayListHomeSummaryBodyItems);

        RecyclerView.LayoutManager rvlmLayoutManager = new LinearLayoutManager(coxContext);
        recyclerViewHomeSummaryBody.setLayoutManager(rvlmLayoutManager);
        recyclerViewHomeSummaryBody.setHasFixedSize(true);
        recyclerViewHomeSummaryBody.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {}

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}

        });
        recyclerViewHomeSummaryBody.setAdapter(clsAdapterHomeSummaryBody);

    }


    /**
     * RecyclerView.ViewHolder class for RecyclerView.Adapter for Home Summary RecyclerView.
     */
    protected static class RVHomeSummaryViewHolder extends RecyclerView.ViewHolder {

        private TextView txtYear;
        private TextView txtAmountReceived;
        private TextView txtAmountSpent;

        private RecyclerView rvHomeSummaryBody;


        protected RVHomeSummaryViewHolder(View itemView) {
            super(itemView);

            txtYear = (TextView) itemView.findViewById(R.id.txtHomeSummaryYear);
            txtAmountReceived = (TextView) itemView.findViewById(R.id.txtHomeSummaryAmountReceived);
            txtAmountSpent = (TextView) itemView.findViewById(R.id.txtHomeSummaryAmountSpent);

            rvHomeSummaryBody = (RecyclerView) itemView.findViewById(R.id.rvHomeSummaryBody);

        }

    }

}
