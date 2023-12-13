package com.nikdroinf.carparkingwatchmanapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingwatchmanapp.Comman.Config;
import com.nikdroinf.carparkingwatchmanapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Plot1Activity extends AppCompatActivity {

    Button btn_slot1,btn_slot2,btn_slot3,btn_slot4,btn_slot5,btn_slot6,btn_slot7,btn_slot8,btn_slot9,btn_slot10;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressBar progress;
    String slot1 = "Slot 1";
    String slot2 = "Slot 2";
    String slot3 = "Slot 3";
    String slot4 = "Slot 4";
    String slot5 = "Slot 5";
    String slot6 = "Slot 6";
    String slot7 = "Slot 7";
    String slot8 = "Slot 8";
    String slot9 = "Slot 9";
    String slot10 = "Slot 10";

    String plot1= "Plot 1";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot1);
        
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        progress = findViewById(R.id.progress);
        
        btn_slot1 = findViewById(R.id.btn_slot1);
        btn_slot2 = findViewById(R.id.btn_slot2);
        btn_slot3 = findViewById(R.id.btn_slot3);
        btn_slot4 = findViewById(R.id.btn_slot4);
        btn_slot5 = findViewById(R.id.btn_slot5);
        btn_slot6 = findViewById(R.id.btn_slot6);
        btn_slot7 = findViewById(R.id.btn_slot7);
        btn_slot8 = findViewById(R.id.btn_slot8);
        btn_slot9 = findViewById(R.id.btn_slot9);
        btn_slot10 = findViewById(R.id.btn_slot10);

        getAllBooking();

        btn_slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot1);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        btn_slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot2);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        btn_slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot3);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        btn_slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot4);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }


        });

        btn_slot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot5);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        btn_slot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot6);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        btn_slot7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot7);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        btn_slot8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot8);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        btn_slot9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot9);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        btn_slot10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot1Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot10);
                intent.putExtra("Plot",plot1);
                startActivity(intent);
            }
        });

        setTitle("slot 1");


    }

    private void getAllBooking() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.url_get_booking, params, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        progress.setVisibility(View.VISIBLE);
                        super.onStart();
                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        progress.setVisibility(View.GONE);

                        try {
                            JSONArray jsonArray = response.getJSONArray("getAllBooking");
                            for (int i = 0; i <jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String plot_name = jsonObject.getString("plot_name");
                                String slot_name = jsonObject.getString("slot_name");

                                if (plot_name.equals(plot1) && slot_name.equals(slot1))
                                {
                                    btn_slot1.setBackgroundColor(Color.RED);
                                    btn_slot1.setClickable(false);

                                }
                                else if (plot_name.equals(plot1) && slot_name.equals(slot2))
                                {
                                    btn_slot2.setBackgroundColor(Color.RED);
                                    btn_slot2.setClickable(false);
                                }
                                else if (plot_name.equals(plot1) && slot_name.equals(slot3))
                                {
                                    btn_slot3.setBackgroundColor(Color.RED);
                                    btn_slot3.setClickable(false);
                                }
                                else if (plot_name.equals(plot1) && slot_name.equals(slot4))
                                {
                                    btn_slot4.setBackgroundColor(Color.RED);
                                    btn_slot3.setClickable(false);
                                }
                                else if (plot_name.equals(plot1) && slot_name.equals(slot5))
                                {
                                    btn_slot5.setBackgroundColor(Color.RED);
                                    btn_slot5.setClickable(false);
                                }
                                else if (plot_name.equals(plot1) && slot_name.equals(slot6))
                                {
                                    btn_slot6.setBackgroundColor(Color.RED);
                                    btn_slot6.setClickable(false);
                                }
                                else if (plot_name.equals(plot1) && slot_name.equals(slot7))
                                {
                                    btn_slot7.setBackgroundColor(Color.RED);
                                    btn_slot7.setClickable(false);
                                }
                                else if (plot_name.equals(plot1) && slot_name.equals(slot8))
                                {
                                    btn_slot8.setBackgroundColor(Color.RED);
                                    btn_slot8.setClickable(false);
                                }
                                else if (plot_name.equals(plot1) && slot_name.equals(slot9))
                                {
                                    btn_slot9.setBackgroundColor(Color.RED);
                                    btn_slot9.setClickable(false);
                                }
                                else if (plot_name.equals(plot1) &&  slot_name.equals(slot10))
                                {
                                    btn_slot10.setBackgroundColor(Color.RED);
                                    btn_slot10.setClickable(false);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(Plot1Activity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Plot1Activity.this, AvailablePlotActivity.class));
        finish();
    }
}
