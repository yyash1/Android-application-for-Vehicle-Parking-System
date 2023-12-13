package com.nikdroinf.carparkingwatchmanapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingwatchmanapp.Comman.Config;
import com.nikdroinf.carparkingwatchmanapp.HomePage;
import com.nikdroinf.carparkingwatchmanapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class ConfirmBookSlotActivity extends AppCompatActivity {

    TextView txt_book_plot_name,txt_book_slot_name,txt_book_vehicle_no,txt_book_select_date_of_parking,txt_book_select_time_of_parking;
    Button btn_confirm_parking;

    ProgressBar progress;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatePickerDialog datePickerDialog;

    String aa,bb;
    String booked = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_book_slot);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        progress = findViewById(R.id.progress);
        txt_book_plot_name = findViewById(R.id.txt_book_plot_name);
        txt_book_slot_name = findViewById(R.id.txt_book_slot_name);
        txt_book_vehicle_no = findViewById(R.id.txt_book_vehicle_no);
        txt_book_select_date_of_parking = findViewById(R.id.txt_book_select_date_of_parking);
        txt_book_select_time_of_parking = findViewById(R.id.txt_book_select_time_of_parking);
        btn_confirm_parking  =findViewById(R.id.btn_confirm_parking);

         aa = getIntent().getStringExtra("Slot");
         bb = getIntent().getStringExtra("Plot");
        txt_book_slot_name.setText(aa);
        txt_book_plot_name.setText(bb);

        String cc = preferences.getString("vehicle_no","");
        txt_book_vehicle_no.setText(cc);
        Toast.makeText(this, ""+aa+bb+cc, Toast.LENGTH_SHORT).show();

        txt_book_select_date_of_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(ConfirmBookSlotActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                txt_book_select_date_of_parking.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        txt_book_select_time_of_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(ConfirmBookSlotActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_book_select_time_of_parking.setText(selectedHour + ":" + selectedMinute);
                        Toast.makeText(ConfirmBookSlotActivity.this, ""+txt_book_select_time_of_parking, Toast.LENGTH_SHORT).show();
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        btn_confirm_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_book_select_date_of_parking.getText().toString().equals("Select Date"))
                {
                    txt_book_select_date_of_parking.setError("Please Select the date");
                }
                else if(txt_book_select_time_of_parking.getText().toString().equals("Select Time"))
                {
                    txt_book_select_time_of_parking.setError("Please Select the time");
                }
                else
                {
                    addconfirmbooking();
                }
            }
        });
    }

    private void addconfirmbooking() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("plot_name",txt_book_plot_name.getText().toString());
        params.put("slot_name",txt_book_slot_name.getText().toString());
        params.put("booked",booked);
        params.put("vehicle_no",preferences.getString("vehicle_no",""));
        params.put("date",txt_book_select_date_of_parking.getText().toString());
        params.put("time",txt_book_select_time_of_parking.getText().toString());

        client.post(Config.url_confirm_booking,params,new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                progress.setVisibility(View.GONE);
                try {
                    String aa = response.getString("success");

                    if (aa.equals("1"))
                    {
                        Toast.makeText(ConfirmBookSlotActivity.this, "Car Slot Alloted Succesfully", Toast.LENGTH_SHORT).show();
                        thankyou();
                    }
                    else
                    {
                        Toast.makeText(ConfirmBookSlotActivity.this, "Already Allot", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(ConfirmBookSlotActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void thankyou() {

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Car Park Successfully");
        ad.setMessage("Thank you for Successfully Allot the Slot");
        ad.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ConfirmBookSlotActivity.this, HomePage.class));
                finish();
            }
        }).create().show();
    }
}
