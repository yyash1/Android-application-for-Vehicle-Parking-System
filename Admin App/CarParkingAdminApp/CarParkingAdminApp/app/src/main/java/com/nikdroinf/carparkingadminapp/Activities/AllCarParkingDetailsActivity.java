package com.nikdroinf.carparkingadminapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingadminapp.Adapter.AllCarParkingDetailsAdapter;
import com.nikdroinf.carparkingadminapp.Adapter.ViewBookingAdapter;
import com.nikdroinf.carparkingadminapp.Comman.Config;
import com.nikdroinf.carparkingadminapp.HomePage;
import com.nikdroinf.carparkingadminapp.PojoClass.pojo_All_Car_Parking_Details;
import com.nikdroinf.carparkingadminapp.PojoClass.pojo_View_Booking;
import com.nikdroinf.carparkingadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AllCarParkingDetailsActivity extends AppCompatActivity {

    List<pojo_All_Car_Parking_Details> list;
    AllCarParkingDetailsAdapter adapter;
    TextView tv_no_record;
    ListView lv_all_view_booking;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_car_parking_details);

        setTitle("View All Car Parking Data");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        list = new ArrayList<>();
        lv_all_view_booking = findViewById(R.id.lv_all_view_booking);
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
                        String book_vehical_no = jsonObject.getString("vehicle_no");
                        String plot_name = jsonObject.getString("plot_name");
                        String slot_name = jsonObject.getString("slot_name");
                        String start_date_of_booking = jsonObject.getString("start_date_of_booking");
                        String end_date_of_booking = jsonObject.getString("end_date_of_booking");
                        String start_time_of_booking = jsonObject.getString("start_time_of_booking");
                        String end_time_of_booking = jsonObject.getString("end_time_of_booking");

                        list.add(new pojo_All_Car_Parking_Details(id,book_vehical_no,plot_name,slot_name,start_date_of_booking,end_date_of_booking
                        ,start_time_of_booking,end_time_of_booking));
                    }

                    adapter = new AllCarParkingDetailsAdapter(list,AllCarParkingDetailsActivity.this,tv_no_record);
                    lv_all_view_booking.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(AllCarParkingDetailsActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AllCarParkingDetailsActivity.this, HomePage.class));
        finish();
    }
}
