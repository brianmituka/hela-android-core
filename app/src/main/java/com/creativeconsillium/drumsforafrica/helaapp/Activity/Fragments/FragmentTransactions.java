package com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter.AdapterVPRTransactions;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentTransactions extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragTransactions = inflater.inflate(R.layout.fragment_transactions1, container,false);
        initializeVariablesAndUIObjects(vFragTransactions);

        return vFragTransactions;
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

        AppCompatActivity acactParentActivity = (AppCompatActivity) getActivity();
        if (acactParentActivity != null) {

            ActionBar abActivityActionBar = acactParentActivity.getSupportActionBar();
            if (abActivityActionBar != null) {
                abActivityActionBar.setTitle(getString(R.string.titleTransactions));
            }

        }

        final ViewPager vprTransactions = (ViewPager) fragmentLayout.findViewById(R.id.pager);

        TabLayout tabLayout = (TabLayout) fragmentLayout.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vprTransactions);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vprTransactions.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

        });

        vprTransactions.setAdapter(new AdapterVPRTransactions(getChildFragmentManager(), tabLayout.getTabCount()));
        vprTransactions.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}