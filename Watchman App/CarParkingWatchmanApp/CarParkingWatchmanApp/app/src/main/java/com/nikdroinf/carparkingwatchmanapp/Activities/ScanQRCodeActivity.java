package com.nikdroinf.carparkingwatchmanapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nikdroinf.carparkingwatchmanapp.HomePage;
import com.nikdroinf.carparkingwatchmanapp.R;

public class ScanQRCodeActivity extends AppCompatActivity {

    private TextView txt_scan_data;
    private Button btn_scan;
    private Button btn_click_here_to_book_parking_slot;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_q_r_code);

        setTitle("Scan QR Code");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        txt_scan_data = findViewById(R.id.txt_scan_qr_code_data);
        btn_scan = (Button) findViewById(R.id.btn_scan);
        btn_click_here_to_book_parking_slot = findViewById(R.id.btn_click_here_to_book_parking_slot);

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(ScanQRCodeActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        btn_click_here_to_book_parking_slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScanQRCodeActivity.this, AvailablePlotActivity.class));
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "scanning has been cancelled", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                txt_scan_data.setText(result.getContents());
                editor.putString("vehicle_no",txt_scan_data.getText().toString()).commit();
                btn_click_here_to_book_parking_slot.setVisibility(View.VISIBLE);
                btn_scan.setVisibility(View.GONE);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ScanQRCodeActivity.this, HomePage.class));
        finish();
    }
}
