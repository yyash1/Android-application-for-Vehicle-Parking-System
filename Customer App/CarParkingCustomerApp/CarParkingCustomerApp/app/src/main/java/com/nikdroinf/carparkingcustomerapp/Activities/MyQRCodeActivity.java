package com.nikdroinf.carparkingcustomerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingcustomerapp.Comman.Config;
import com.nikdroinf.carparkingcustomerapp.HomePage;
import com.nikdroinf.carparkingcustomerapp.QRCodeScannerActivity;
import com.nikdroinf.carparkingcustomerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyQRCodeActivity extends AppCompatActivity {

    ImageView imageView;
//    Button button;

    Thread thread ;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressBar progress;
    TextView tv_no_record;
    TextView txt_name,txt_mobile_no,txt_vehicle_no;
    String ed1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_q_r_code);

        setTitle("My QR Code");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);
        imageView = (ImageView)findViewById(R.id.imageView);

        txt_name = findViewById(R.id.txt_name);
        txt_vehicle_no = findViewById(R.id.txt_vehicle_no);
        txt_mobile_no = findViewById(R.id.txt_mobile_no);

//        button = (Button)findViewById(R.id.button);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {


                getMyDetailsToCreateQR();


//            }
//        });

    }

    private void getMyDetailsToCreateQR() {


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
//                                String name = jsonObject.getString("name");
                                String vehicle_no = jsonObject.getString("vehicle_no");
//                                String mobile_no = jsonObject.getString("mobile_no");

//                                txt_name.setText(name);
                                txt_vehicle_no.setText(vehicle_no);
//                                txt_mobile_no.setText(mobile_no);

                                ed1 = txt_vehicle_no.getText().toString();

                                try {
                                    bitmap = TextToImageEncode(ed1);

                                    imageView.setImageBitmap(bitmap);

                                } catch (WriterException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(MyQRCodeActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyQRCodeActivity.this, HomePage.class));
        finish();
    }
}