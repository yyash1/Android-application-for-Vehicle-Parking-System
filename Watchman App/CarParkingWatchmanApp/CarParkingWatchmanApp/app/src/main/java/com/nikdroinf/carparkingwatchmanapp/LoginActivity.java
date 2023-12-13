package com.nikdroinf.carparkingwatchmanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingwatchmanapp.Comman.Config;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    boolean doubletap = false;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    EditText edt_watchmen_id, edt_password;
    Button btn_sign_in;
    CheckBox show_hide;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        edt_watchmen_id = findViewById(R.id.edt_watchmen_id);
        edt_password = findViewById(R.id.edt_password);
        btn_sign_in = findViewById(R.id.btn_login);
        show_hide = findViewById(R.id.chk_Show_hide);
        progress = findViewById(R.id.progress);

        show_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_watchmen_id.getText().toString().isEmpty())
                {
                    edt_watchmen_id.setError("Please Enter Valid Watchmen ID");
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
    }

    private void login()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("watchmen_id",edt_watchmen_id.getText().toString());
        params.put("watchmen_password",edt_password.getText().toString());

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
                    editor.putString("watchmen_id",edt_watchmen_id.getText().toString()).commit();
                    String aa = response.getString("success");
                    if (aa.equals("1"))
                    {
                        Toast.makeText(LoginActivity.this, "Login Successfully Done", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,HomePage.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Incorrect Watchman ID or Password", Toast.LENGTH_SHORT).show();
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
