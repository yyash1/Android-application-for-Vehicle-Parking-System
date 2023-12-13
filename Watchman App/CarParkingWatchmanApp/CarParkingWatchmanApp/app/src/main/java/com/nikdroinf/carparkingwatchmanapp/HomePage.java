package com.nikdroinf.carparkingwatchmanapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.nikdroinf.carparkingwatchmanapp.Activities.AvailablePlotActivity;
import com.nikdroinf.carparkingwatchmanapp.Activities.MyProfileActivity;
import com.nikdroinf.carparkingwatchmanapp.Activities.RemoveCarActivity;
import com.nikdroinf.carparkingwatchmanapp.Activities.ScanQRCodeActivity;
import com.nikdroinf.carparkingwatchmanapp.Activities.ViewAvailablePlotActivity;

public class HomePage extends AppCompatActivity {

    CardView cardView11,cardView22,cardView33,cardView44,cardView55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setTitle("Security Guard App");

        SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firsttime = sharedPreferences.getBoolean("firsttime",true);

        if (firsttime)
        {
            welcome();
        }

        cardView11 = findViewById(R.id.cardview11);
        cardView22 = findViewById(R.id.cardview22);
        cardView33 = findViewById(R.id.cardview33);
        cardView44 = findViewById(R.id.cardview44);
        cardView55 = findViewById(R.id.cardview55);

        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, ScanQRCodeActivity.class));
                finish();
            }
        });

        cardView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, ViewAvailablePlotActivity.class));
                finish();
            }
        });

        cardView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, RemoveCarActivity.class));
                finish();
            }
        });

        cardView44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, MyProfileActivity.class));
                finish();
            }
        });

        cardView55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

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
//                editor.putBoolean("islogin",false).commit();
                startActivity(new Intent(HomePage.this,LoginActivity.class));
                finish();
            }
        });

        Dialog dialog = ad.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void welcome() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Security Guard App");
        ad.setMessage("Welcome to Security Guard App");
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
}
