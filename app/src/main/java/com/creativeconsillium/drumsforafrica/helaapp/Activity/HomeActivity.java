package com.creativeconsillium.drumsforafrica.helaapp.Activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Interface.InterfaceBudgets;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FormatUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.PreferenceUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.SmsUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.TransactionsUtil;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.UiUtils;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements InterfaceBudgets {

    private FragmentTransaction ftHome;
    String TAG = HomeActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Code to add logo to app action bar
        getSupportActionBar().setDisplayOptions(getSupportActionBar().getDisplayOptions() | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(getSupportActionBar().getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.logo_top);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.END | Gravity.RIGHT );
        layoutParams.rightMargin = 16;
        imageView.setLayoutParams(layoutParams);
        getSupportActionBar().setCustomView(imageView);
        getSupportActionBar().setElevation(0);  // Remove drop shadow from action bar

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_budgets, R.id.navigation_transactions, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    @Override
    protected void onResume(){


        if (!PreferenceUtils.isMpesaSynced(getApplicationContext())){
            new uploadMessages().execute();
        }
        super.onResume();
    }


    /**
     * This method shows an AlertDialog that asks the user to confirm if they want to Exit the App.
     *
     * Called In:
     *          - (Override) this.onBackPressed();
     */
    private void codeToShowAppExitConfirmationDialog() {

        AlertDialog.Builder adbldAppExitConfirmation = new AlertDialog.Builder(HomeActivity.this);
        adbldAppExitConfirmation.setIcon(getDrawable(android.R.drawable.ic_dialog_alert));
        adbldAppExitConfirmation.setTitle(getString(R.string.dgtitleConfirmExit));
        adbldAppExitConfirmation.setMessage(getString(R.string.dgmsgConfirmExit));
        adbldAppExitConfirmation.setCancelable(false);
        adbldAppExitConfirmation.setPositiveButton(getString(R.string.dgbtnYesExit), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                HomeActivity.this.finish();
            }

        });
        adbldAppExitConfirmation.setNegativeButton(getString(R.string.dgbtnNo), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }

        });

        AlertDialog adgAppExitConfirmation = adbldAppExitConfirmation.create();
        adgAppExitConfirmation.show();

    }

    /**
     * Method to set up Fragment Budgets Detail.
     *
     * Called In:
     *          - (Override) this.InterfaceBudgets.codeToLaunchBudgetDetail();
     */
    private void codeToSetUpFragmentBudgetsDetail() {

        Navigation.findNavController(HomeActivity.this, R.id.nav_host_fragment).navigate(R.id.nav_frag_budgets_detail);

    }


    //  [START]: InterfaceBudgets

    @Override
    public void codeToLaunchBudgetDetail() {

        codeToSetUpFragmentBudgetsDetail();

    }

    private class uploadMessages extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            UiUtils.showDialog("Hela is setting up", HomeActivity.this);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0){
            SmsUtils.getMpesaMessages(getApplicationContext());
            TransactionsUtil.getMonthReceivedAmount();
            TransactionsUtil.getMonthSpentAmount();


//            TransactionsUtil.getTransactionSummary();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            UiUtils.hideDialog();
            super.onPostExecute(aVoid);
        }
    }



}
