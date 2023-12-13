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

public class Plot4Activity extends AppCompatActivity {


    Button btn_slot1,btn_slot2,btn_slot3,btn_slot4,btn_slot5,btn_slot6;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressBar progress;
    String slot1 = "Slot 1";
    String slot2 = "Slot 2";
    String slot3 = "Slot 3";
    String slot4 = "Slot 4";
    String slot5 = "Slot 5";
    String slot6 = "Slot 6";

    String plot4 = "Plot 4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot4);

        setTitle("PLOT 4");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        progress = findViewById(R.id.progress);

        btn_slot1 = findViewById(R.id.btn_slot1);
        btn_slot2 = findViewById(R.id.btn_slot2);
        btn_slot3 = findViewById(R.id.btn_slot3);
        btn_slot4 = findViewById(R.id.btn_slot4);
        btn_slot5 = findViewById(R.id.btn_slot5);
        btn_slot6 = findViewById(R.id.btn_slot6);

        getAllBooking();

        btn_slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Plot4Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot1);
                intent.putExtra("Plot",plot4);
                startActivity(intent);
            }
        });

        btn_slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot4Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot2);
                intent.putExtra("Plot",plot4);
                startActivity(intent);
            }
        });

        btn_slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot4Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot3);
                intent.putExtra("Plot",plot4);
                startActivity(intent);
            }
        });

        btn_slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot4Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot4);
                intent.putExtra("Plot",plot4);
                startActivity(intent);
            }


        });

        btn_slot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot4Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot5);
                intent.putExtra("Plot",plot4);
                startActivity(intent);
            }
        });

        btn_slot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plot4Activity.this,ConfirmBookSlotActivity.class);
                intent.putExtra("Slot",slot6);
                intent.putExtra("Plot",plot4);
                startActivity(intent);
            }
        });
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

                                if (plot_name.equals(plot4) && slot_name.equals(slot1))
                                {
                                    btn_slot1.setBackgroundColor(Color.RED);
                                    btn_slot1.setClickable(false);

                                }
                                else if (plot_name.equals(plot4) && slot_name.equals(slot2))
                                {
                                    btn_slot2.setBackgroundColor(Color.RED);
                                    btn_slot2.setClickable(false);
                                }
                                else if (plot_name.equals(plot4) && slot_name.equals(slot3))
                                {
                                    btn_slot3.setBackgroundColor(Color.RED);
                                    btn_slot3.setClickable(false);
                                }
                                else if (plot_name.equals(plot4) && slot_name.equals(slot4))
                                {
                                    btn_slot4.setBackgroundColor(Color.RED);
                                    btn_slot4.setClickable(false);
                                }
                                else if (plot_name.equals(plot4) && slot_name.equals(slot5))
                                {
                                    btn_slot5.setBackgroundColor(Color.RED);
                                    btn_slot5.setClickable(false);
                                }
                                else if (plot_name.equals(plot4) && slot_name.equals(slot6))
                                {
                                    btn_slot6.setBackgroundColor(Color.RED);
                                    btn_slot6.setClickable(false);
                                }



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(Plot4Activity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(Plot4Activity.this, AvailablePlotActivity.class));
        finish();
    }
}
