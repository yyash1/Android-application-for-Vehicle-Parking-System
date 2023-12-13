package com.nikdroinf.carparkingadminapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingadminapp.Comman.Config;
import com.nikdroinf.carparkingadminapp.HomePage;
import com.nikdroinf.carparkingadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AddWatchmanActivity extends AppCompatActivity {

    EditText edt_watchman_id,edt_watchman_name,edt_watchman_mobile_no,edt_watchman_password;
    Button btn_add_watchman;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_watchman);

        setTitle("Add Watchman");
        edt_watchman_id = findViewById(R.id.edt_watchman_id);
        edt_watchman_name = findViewById(R.id.edt_watchman_name);
        edt_watchman_mobile_no = findViewById(R.id.edt_watchman_mobile_no);
        edt_watchman_password = findViewById(R.id.edt_watchman_password);
        btn_add_watchman = findViewById(R.id.btn_add_watchman);
        progress = findViewById(R.id.progress);

        btn_add_watchman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_watchman_id.getText().toString().isEmpty())
                {
                    edt_watchman_id.setError("Please Enter Watchman ID");
                }
                else if (edt_watchman_name.getText().toString().isEmpty())
                {
                    edt_watchman_name.setError("Please Enter Watchman Name");
                }
                else if (edt_watchman_mobile_no.getText().toString().isEmpty())
                {
                    edt_watchman_mobile_no.setError("Please Enter Watchman Mobile Number");
                }
                else if (edt_watchman_password.getText().toString().isEmpty())
                {
                    edt_watchman_password.setError("Please Enter Watchman Strong Password");
                }
                else
                {
                    addWatchman();
                }

            }
        });

    }

    private void addWatchman() {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("watchmen_id",edt_watchman_id.getText().toString());
        params.put("watchmen_name",edt_watchman_name.getText().toString());
        params.put("watchmen_mobile_no",edt_watchman_mobile_no.getText().toString());
        params.put("watchmen_password",edt_watchman_password.getText().toString());

        client.post(Config.url_add_watchmen,params,new JsonHttpResponseHandler() {
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
                        Toast.makeText(AddWatchmanActivity.this, "Watchman Account Added Succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddWatchmanActivity.this,HomePage.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(AddWatchmanActivity.this, "Already Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(AddWatchmanActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddWatchmanActivity.this, HomePage.class));
        finish();
    }
}
