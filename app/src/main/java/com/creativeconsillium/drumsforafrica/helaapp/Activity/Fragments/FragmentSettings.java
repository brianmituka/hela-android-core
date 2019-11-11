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


public class FragmentSettings extends Fragment{

    private Context coxContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.coxContext = context;

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragSettings =inflater.inflate(R.layout.fragment_settings,container,false);
        initializeVariablesAndUIObjects(vFragSettings);

        return vFragSettings;
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

        CardView cardProfile = fragmentLayout.findViewById(R.id.cardSettingsProfile);
        CardView cardSecurity = fragmentLayout.findViewById(R.id.cardSettingsSecurity);
        CardView cardBudgets = fragmentLayout.findViewById(R.id.cardSettingsBudgets);
        CardView cardBackup = fragmentLayout.findViewById(R.id.cardSettingsBackup);
        CardView cardAbout = fragmentLayout.findViewById(R.id.cardSettingsAbout);
        cardProfile.setOnClickListener(clkSettingsProfile);
        cardSecurity.setOnClickListener(clkSettingsSecurity);
        cardBudgets.setOnClickListener(clkSettingsBudgets);
        cardBackup.setOnClickListener(clkSettingsBackup);
        cardAbout.setOnClickListener(clkSettingsAbout);

    }


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkSettingsProfile = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(FragmentSettings.this).navigate(R.id.nav_frag_settings_profile);

        }

    };


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkSettingsSecurity = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(FragmentSettings.this).navigate(R.id.nav_frag_settings_security);

        }

    };


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkSettingsBudgets = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(FragmentSettings.this).navigate(R.id.nav_frag_settings_budgets);

        }

    };


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkSettingsBackup = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(FragmentSettings.this).navigate(R.id.nav_frag_settings_backup);

        }

    };


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented in:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkSettingsAbout = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(FragmentSettings.this).navigate(R.id.nav_frag_settings_about);

        }

    };


}
