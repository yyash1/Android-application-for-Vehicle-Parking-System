package com.nikdroinf.carparkingadminapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.nikdroinf.carparkingadminapp.Activities.AllCarParkingDetailsActivity;
import com.nikdroinf.carparkingadminapp.Activities.AddWatchmanActivity;
import com.nikdroinf.carparkingadminapp.Activities.AllUsersActivity;
import com.nikdroinf.carparkingadminapp.Activities.FeedbackActivity;
import com.nikdroinf.carparkingadminapp.Activities.ViewBookingActivity;

public class HomePage extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    CardView cardview11,cardview22,cardview33,cardview44,cardview55,cardview66;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Car Parking Admin App");
        actionBar.setDisplayShowHomeEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        cardview11 = findViewById(R.id.cardview11);
        cardview22 = findViewById(R.id.cardview22);
        cardview33 = findViewById(R.id.cardview33);
        cardview44 = findViewById(R.id.cardview44);
        cardview55 = findViewById(R.id.cardview55);
        cardview66 = findViewById(R.id.cardview66);


        cardview11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, AllCarParkingDetailsActivity.class));
                finish();

            }
        });

        cardview22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, ViewBookingActivity.class));
                finish();

            }
        });

        cardview33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, AllUsersActivity.class));
                finish();
            }
        });

        cardview44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, FeedbackActivity.class));
                finish();
            }
        });

        cardview55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, AddWatchmanActivity.class));
                finish();
            }
        });

        cardview66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firsttime = sharedPreferences.getBoolean("firsttime",true);

        if (firsttime)
        {
            welcome();
        }
    }

    private void welcome() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Car Parking");
        ad.setIcon(R.drawable.logo);
        ad.setMessage("Welcome to Admin Panel of Car Parking");
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).create().show();

        SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firsttime",false);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        logout();
    }

    public void logout()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Logout");
        ad.setMessage("Are You Want To Logout");
        ad.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        ad.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.putBoolean("islogin",false).commit();
                startActivity(new Intent(HomePage.this,LoginActivity.class));
                finish();
            }
        });

        Dialog dialog = ad.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
