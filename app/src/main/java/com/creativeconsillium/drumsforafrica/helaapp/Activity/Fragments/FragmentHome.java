package com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.creativeconsillium.drumsforafrica.helaapp.R;

public class FragmentHome extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragHome = inflater.inflate(R.layout.fragment_home, container, false);
        initializeVariablesAndUIObjects(vFragHome);

        return vFragHome;
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

        CardView cardSummary = fragmentLayout.findViewById(R.id.cardHomeSummary);
        cardSummary.setOnClickListener(clkHomeSummary);

    }


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkHomeSummary = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(FragmentHome.this).navigate(R.id.nav_frag_home_summary);

        }

    };


}