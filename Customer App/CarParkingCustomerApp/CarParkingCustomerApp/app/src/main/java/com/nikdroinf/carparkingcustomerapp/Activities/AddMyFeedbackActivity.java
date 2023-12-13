package com.nikdroinf.carparkingcustomerapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingcustomerapp.Comman.Config;
import com.nikdroinf.carparkingcustomerapp.HomePage;
import com.nikdroinf.carparkingcustomerapp.LoginActivity;
import com.nikdroinf.carparkingcustomerapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AddMyFeedbackActivity extends AppCompatActivity {
    
    EditText edt_mobile_no,edt_feedback;
    Button btn_submit;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_feedback);
        
        edt_mobile_no = findViewById(R.id.edt_mobile_no);
        edt_feedback = findViewById(R.id.edt_feedback);
        btn_submit = findViewById(R.id.btn_submit);
        progress = findViewById(R.id.progress);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_mobile_no.getText().toString().isEmpty())
                {
                    edt_mobile_no.setError("Please Enter Valid Mobile Number");
                }
                else if (edt_feedback.getText().toString().isEmpty())
                {
                    edt_feedback.setError("Please Enter Feedback");
                }
                else
                {
                    addMyFeedback();
                }
            }
        });
    }

    private void addMyFeedback() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("vehicle_no",preferences.getString("vehicle_no",""));
        params.put("mobile_no",edt_mobile_no.getText().toString());
        params.put("feedback",edt_feedback.getText().toString());


        client.post(Config.url_add_my_feedback,params,new JsonHttpResponseHandler() {
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
                        Toast.makeText(AddMyFeedbackActivity.this, "Feedback Succesfully Added", Toast.LENGTH_SHORT).show();
                        thankyou();
                    }
                    else
                    {
                        Toast.makeText(AddMyFeedbackActivity.this, "Already Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(AddMyFeedbackActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void thankyou() {

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Car Parking Application");
        ad.setMessage("Thank you for added your valuable feedback");
        ad.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(AddMyFeedbackActivity.this,HomePage.class));
                finish();
            }
        }).create().show();
    }
}
