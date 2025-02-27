package com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.creativeconsillium.drumsforafrica.helaapp.R;

public class FragmentSettingsAbout extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        View vFragSettingsProfile =inflater.inflate(R.layout.fragment_settings_about,container,false);
        initializeVariablesAndUIObjects(vFragSettingsProfile);

        return vFragSettingsProfile;



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

        ImageView imgvBack = (ImageView) fragmentLayout.findViewById(R.id.imgvSettingsProfileBackArrow);
        imgvBack.setOnClickListener(clkSettingsAbout);

        AppCompatActivity acactParentActivity = (AppCompatActivity) getActivity();
        if (acactParentActivity != null) {

            ActionBar abActivityActionBar = acactParentActivity.getSupportActionBar();
            if (abActivityActionBar != null) {
                abActivityActionBar.setTitle(getString(R.string.profileAboutHela));
                abActivityActionBar.setDisplayHomeAsUpEnabled(false);
            }

        }

    }


    /**
     * Method to handle back/up navigation from this Fragment.
     *
     * Called In:
     *          - (Override) this.clkSettingsProfile.onClick();
     */
    private void codeToNavigateBack() {

        NavHostFragment.findNavController(FragmentSettingsAbout.this).navigateUp();

    }


    /**
     * View.OnClickListener interface for handling clicks on views on this Fragment.
     *
     * Implemented In:
     *          - this.initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkSettingsAbout = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.imgvSettingsProfileBackArrow:
                    codeToNavigateBack();
                    break;

            }

        }

    };


}
