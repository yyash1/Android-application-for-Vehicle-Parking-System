package com.nikdroinf.carparkingwatchmanapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nikdroinf.carparkingwatchmanapp.R;

public class AvailablePlotActivity extends AppCompatActivity {

    TextView txt_title_of_the_screen;
    Button btn_plot1,btn_plot2,btn_plot3,btn_plot4;
    String plot1 = "Plot 1";
    String plot2 = "Plot 2";
    String plot3 = "Plot 3";
    String plot4 = "Plot 4";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_plot);

        setTitle("Available Plots");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        txt_title_of_the_screen = findViewById(R.id.txt_title_of_screen);
        btn_plot1 = findViewById(R.id.btn_plot1);
        btn_plot2 = findViewById(R.id.btn_plot2);
        btn_plot3 = findViewById(R.id.btn_plot3);
        btn_plot4 = findViewById(R.id.btn_plot4);

        String aa = preferences.getString("vehicle_no","");
        Toast.makeText(this, ""+aa, Toast.LENGTH_SHORT).show();
        txt_title_of_the_screen.setText(aa);

        btn_plot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AvailablePlotActivity.this,Plot1Activity.class);
                intent.putExtra("Plot 1",plot1);
                startActivity(intent);
            }
        });

        btn_plot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AvailablePlotActivity.this,Plot2Activity.class);
                intent.putExtra("Plot 2",plot2);
                startActivity(intent);
            }
        });

        btn_plot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AvailablePlotActivity.this,Plot3Activity.class);
                intent.putExtra("Plot 3",plot3);
                startActivity(intent);
            }
        });

        btn_plot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AvailablePlotActivity.this,Plot4Activity.class);
                intent.putExtra("Plot 4",plot4);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AvailablePlotActivity.this,ScanQRCodeActivity.class));
        finish();
    }
}
