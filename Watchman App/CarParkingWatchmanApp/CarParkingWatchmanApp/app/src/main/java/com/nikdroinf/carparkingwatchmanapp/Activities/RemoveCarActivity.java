package com.nikdroinf.carparkingwatchmanapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingwatchmanapp.AdapterClass.ViewBookingForRemoveAdapter;
import com.nikdroinf.carparkingwatchmanapp.Comman.Config;
import com.nikdroinf.carparkingwatchmanapp.HomePage;
import com.nikdroinf.carparkingwatchmanapp.PojoClass.pojo_View_Booking_For_Remove;
import com.nikdroinf.carparkingwatchmanapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RemoveCarActivity extends AppCompatActivity {

    List<pojo_View_Booking_For_Remove> list;
    ViewBookingForRemoveAdapter adapter;
    TextView tv_no_record;
    ListView lv_view_booking;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_car);

        setTitle("View Booking");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        list = new ArrayList<>();
        lv_view_booking = findViewById(R.id.lv_view_booking);
        tv_no_record = findViewById(R.id.tv_no_record);
        progress = findViewById(R.id.progress);

        getAllViewBooking();
    }

    private void getAllViewBooking() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

//        params.put("election_state",preferences.getString("election_state",""));
        client.post(Config.url_all_view_booking, params,new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                progress.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("getAllViewBooking");
                    for (int i= 0 ; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String book_vehincal_no = jsonObject.getString("book_vehincle_no");
                        String plot_name = jsonObject.getString("plot_name");
                        String slot_name = jsonObject.getString("slot_name");
                        String date_of_booking = jsonObject.getString("date_of_booking");
                        String time_of_booking = jsonObject.getString("time_of_booking");

                        list.add(new pojo_View_Booking_For_Remove(id,book_vehincal_no,plot_name,slot_name,date_of_booking,time_of_booking));
                    }

                    adapter = new ViewBookingForRemoveAdapter(list,RemoveCarActivity.this,tv_no_record);
                    lv_view_booking.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(RemoveCarActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RemoveCarActivity.this, HomePage.class));
        finish();
    }
}
