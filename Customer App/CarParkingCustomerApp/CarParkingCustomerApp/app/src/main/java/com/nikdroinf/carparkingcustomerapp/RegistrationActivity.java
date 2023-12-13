package com.nikdroinf.carparkingcustomerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingcustomerapp.Comman.Config;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegistrationActivity extends AppCompatActivity {

    EditText edt_name,edt_email,edt_mobile_no,edt_vehicle_no,edt_retype_password;
    TextInputEditText edt_password;
    Button btn_sign_up;

    ProgressBar progress;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        progress = findViewById(R.id.progress);

        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_mobile_no = findViewById(R.id.edt_mobile_no);
        edt_vehicle_no = findViewById(R.id.edt_vehicle_no);
        edt_retype_password = findViewById(R.id.edt_retype_password);
        edt_password = findViewById(R.id.edt_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_name.getText().toString().isEmpty())
                {
                    edt_name.setError("Please Enter Your Name");
                }
                else if(edt_mobile_no.getText().toString().isEmpty())
                {
                    edt_mobile_no.setError("Please Enter Your Mobile Number");
                }
                else if (edt_email.getText().toString().isEmpty())
                {
                    edt_email.setError("Please Enter Your Mail Id");
                }
                else if (edt_vehicle_no.getText().toString().isEmpty())
                {
                    edt_vehicle_no.setError("Enter Vehicle Number");
                }
                else if (edt_password.getText().toString().isEmpty())
                {
                    edt_password.setError("Enter Strong Password");
                }
                else
                {
                    addUsers();
                }
            }
        });
    }

    public void addUsers()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("name",edt_name.getText().toString());
        params.put("mobile_no",edt_mobile_no.getText().toString());
        params.put("email",edt_email.getText().toString());
        params.put("vehicle_no",edt_vehicle_no.getText().toString());
        params.put("password",edt_password.getText().toString());

        client.post(Config.url_addusers,params,new JsonHttpResponseHandler() {
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
                        Toast.makeText(RegistrationActivity.this, "Registration Succesfully Done", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this, "Already Register", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(RegistrationActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
        finish();
    }
}
