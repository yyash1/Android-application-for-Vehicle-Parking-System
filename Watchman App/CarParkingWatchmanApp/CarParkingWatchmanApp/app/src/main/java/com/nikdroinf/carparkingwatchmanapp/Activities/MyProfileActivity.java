package com.nikdroinf.carparkingwatchmanapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingwatchmanapp.Comman.Config;
import com.nikdroinf.carparkingwatchmanapp.HomePage;
import com.nikdroinf.carparkingwatchmanapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyProfileActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressBar progress;
    TextView tv_no_record;
    TextView txt_watchmen_id,txt_name,txt_mobile_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        setTitle("My Profile");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        tv_no_record = findViewById(R.id.tv_no_record);
        progress = findViewById(R.id.progress);

        txt_watchmen_id = findViewById(R.id.txt_my_profile_id);
        txt_name = findViewById(R.id.txt_my_profile_name);
        txt_mobile_no = findViewById(R.id.txt_my_profile_mobile_no);

        getMyProfile();
    }

    private void getMyProfile() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("watchmen_id",preferences.getString("watchmen_id",""));
        client.post(Config.url_get_my_profile, params, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        progress.setVisibility(View.VISIBLE);
                        super.onStart();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        progress.setVisibility(View.GONE);

                        try {
                            JSONArray jsonArray = response.getJSONArray("getMyProfile");
                            for (int i = 0; i <jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String watchmenid = jsonObject.getString("watchmen_id");
                                String name = jsonObject.getString("watchmen_name");
                                String mobile_no = jsonObject.getString("watchmen_mobile_no");


                                txt_watchmen_id.setText(watchmenid);
                                txt_name.setText(name);
                                txt_mobile_no.setText(mobile_no);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(MyProfileActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyProfileActivity.this, HomePage.class));
        finish();
    }
}
