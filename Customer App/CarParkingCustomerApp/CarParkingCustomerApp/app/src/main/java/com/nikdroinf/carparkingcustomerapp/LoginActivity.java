package com.nikdroinf.carparkingcustomerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingcustomerapp.Comman.Config;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    boolean doubletap = false;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    EditText edt_vehicle_no,edt_password;
    Button btn_login,btn_sign_up;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        edt_vehicle_no = findViewById(R.id.edt_vehicle_no);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_sign_up = findViewById(R.id.btn_register);
        progress = findViewById(R.id.progress);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_vehicle_no.getText().toString().isEmpty())
                {
                    edt_vehicle_no.setError("Please Enter Valid Vehicle Number");
                }
                else if (edt_password.getText().toString().isEmpty())
                {
                    edt_password.setError("Please Enter Valid Password");
                }
                else
                {
                    login();
                }

            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                finish();
            }
        });

    }

    private void login()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("vehicle_no",edt_vehicle_no.getText().toString());
        params.put("password",edt_password.getText().toString());

        client.post(Config.url_login,params,new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                progress.setVisibility(View.GONE);

                try {
                    editor.putString("vehicle_no",edt_vehicle_no.getText().toString()).commit();
                    String aa = response.getString("success");
                    if (aa.equals("1"))
                    {
                        Toast.makeText(LoginActivity.this, "Login Successfully Done", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,HomePage.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Incorrect Vehicle Number or Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(LoginActivity.this, "Colud Not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (doubletap)
        {
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(this, "Press again to exit the app", Toast.LENGTH_SHORT).show();
            doubletap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            },2000);
        }
    }
}
