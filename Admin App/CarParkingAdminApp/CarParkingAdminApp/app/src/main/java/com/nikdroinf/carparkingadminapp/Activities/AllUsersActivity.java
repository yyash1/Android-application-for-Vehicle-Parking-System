package com.nikdroinf.carparkingadminapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingadminapp.Adapter.AllUsersAdapter;
import com.nikdroinf.carparkingadminapp.Adapter.ViewBookingAdapter;
import com.nikdroinf.carparkingadminapp.Comman.Config;
import com.nikdroinf.carparkingadminapp.HomePage;
import com.nikdroinf.carparkingadminapp.PojoClass.pojo_All_Users;
import com.nikdroinf.carparkingadminapp.PojoClass.pojo_View_Booking;
import com.nikdroinf.carparkingadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AllUsersActivity extends AppCompatActivity {

    List<pojo_All_Users> list;
    AllUsersAdapter adapter;
    TextView tv_no_record;
    ListView lv_all_users;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        setTitle("All Users");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        list = new ArrayList<>();
        lv_all_users = findViewById(R.id.lv_all_users);
        tv_no_record = findViewById(R.id.tv_no_record);
        progress = findViewById(R.id.progress);

        getAllUsers();
    }

    private void getAllUsers() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

//        params.put("election_state",preferences.getString("election_state",""));
        client.post(Config.url_all_users, params,new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                progress.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("getAllUsers");
                    for (int i= 0 ; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String vehical_no = jsonObject.getString("vehicle_no");
                        String name = jsonObject.getString("name");
                        String mobile_no = jsonObject.getString("mobile_no");
                        String email_id = jsonObject.getString("email_id");


                        list.add(new pojo_All_Users(vehical_no,name,mobile_no,email_id));
                    }

                    adapter = new AllUsersAdapter(list,AllUsersActivity.this,tv_no_record);
                    lv_all_users.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(AllUsersActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AllUsersActivity.this, HomePage.class));
        finish();
    }
}
