package com.nikdroinf.carparkingcustomerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nikdroinf.carparkingcustomerapp.Activities.AddMyFeedbackActivity;
import com.nikdroinf.carparkingcustomerapp.Activities.MyDetailsActivity;
import com.nikdroinf.carparkingcustomerapp.Activities.MyQRCodeActivity;
import com.nikdroinf.carparkingcustomerapp.Activities.ViewBookingActivity;

public class HomePage extends AppCompatActivity {

    boolean doubletap = false;
    CardView cardView11,cardView22,cardView33,cardView44,cardView55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setTitle("Car Parking");

        cardView11 = findViewById(R.id.cardview11);
        cardView22 = findViewById(R.id.cardview22);
        cardView33 = findViewById(R.id.cardview33);
        cardView44 = findViewById(R.id.cardview44);
        cardView55 = findViewById(R.id.cardview55);

        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, MyQRCodeActivity.class));
                finish();
            }
        });

        cardView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, ViewBookingActivity.class));
                finish();
            }
        });

        cardView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, MyDetailsActivity.class));
                finish();
            }
        });

        cardView44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, AddMyFeedbackActivity.class));
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

    public void logout()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Logout");
        ad.setMessage("Are You Sure For Exit The app");
        ad.setPositiveButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        ad.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(HomePage.this,LoginActivity.class));
                finish();
            }
        }).create().show();
    }



    @Override
    public void onBackPressed() {
        if (doubletap)
        {
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(this, "Press again to exit the app", Toast.LENGTH_SHORT).show();
            doubletap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            },2000);
        }
    }
}
