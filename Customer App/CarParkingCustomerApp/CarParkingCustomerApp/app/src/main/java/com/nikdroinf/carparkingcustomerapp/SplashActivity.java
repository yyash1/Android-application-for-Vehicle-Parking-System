package com.nikdroinf.carparkingcustomerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView txt_app_title;
    ImageView img_app_logo;
    Animation blink, lefttoright1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        txt_app_title = (TextView) findViewById(R.id.txt_app_title);
        img_app_logo = (ImageView) findViewById(R.id.img_app_logo);
//        gif_file = (GifImageView)findViewById(R.id.gif_file);

        lefttoright1 = AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        img_app_logo.startAnimation(lefttoright1);

        new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();

                }
            }
        }.start();
    }
}
