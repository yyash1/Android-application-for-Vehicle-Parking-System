package com.nikdroinf.carparkingcustomerapp.Activities;

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

import com.google.zxing.WriterException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingcustomerapp.AdapterClass.ViewBookingAdapter;
import com.nikdroinf.carparkingcustomerapp.Comman.Config;
import com.nikdroinf.carparkingcustomerapp.HomePage;
import com.nikdroinf.carparkingcustomerapp.PojoClass.pojo_View_Booking;
import com.nikdroinf.carparkingcustomerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MyDetailsActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressBar progress;
    TextView tv_no_record;
    TextView txt_name,txt_mobile_no,txt_email_id,txt_vehicle_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);

        setTitle("My Details");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        tv_no_record = findViewById(R.id.tv_no_record);
        progress = findViewById(R.id.progress);

        txt_name = findViewById(R.id.txt_my_details_user_name);
        txt_mobile_no = findViewById(R.id.txt_my_details_user_mobile_no);
        txt_email_id = findViewById(R.id.txt_my_details_email);
        txt_vehicle_no = findViewById(R.id.txt_my_details_vehicle_no);

        getMyDetails();
    }

    private void getMyDetails() {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("vehicle_no",preferences.getString("vehicle_no",""));
        client.post(Config.url_get_my_details, params, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        progress.setVisibility(View.VISIBLE);
                        super.onStart();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        progress.setVisibility(View.GONE);

                        try {
                            JSONArray jsonArray = response.getJSONArray("getMyDetails");
                            for (int i = 0; i <jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String vehicle_no = jsonObject.getString("vehicle_no");
                                String mobile_no = jsonObject.getString("mobile_no");
                                String email_id = jsonObject.getString("email_id");

                                txt_name.setText(name);
                                txt_vehicle_no.setText(vehicle_no);
                                txt_mobile_no.setText(mobile_no);
                                txt_email_id.setText(email_id);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(MyDetailsActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyDetailsActivity.this, HomePage.class));
        finish();
    }
}
