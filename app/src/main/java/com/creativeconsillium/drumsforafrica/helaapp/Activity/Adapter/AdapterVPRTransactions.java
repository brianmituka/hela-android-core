package com.creativeconsillium.drumsforafrica.helaapp.Activity.Adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments.FragmentTransactionsReceived;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Fragments.FragmentTransactionsSpent;

public class AdapterVPRTransactions extends FragmentPagerAdapter {

    private int mNumOfTabs;

    public AdapterVPRTransactions(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new FragmentTransactionsReceived();

            case 1:
                return new FragmentTransactionsSpent();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "Received";

            case 1:
                return "Spent";

        }

        return null;
    }

}
