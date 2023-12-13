package com.nikdroinf.carparkingwatchmanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView img_logo;
    TextView txt_title;

    Animation blink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txt_title = (TextView) findViewById(R.id.txt_title);
        img_logo = (ImageView)findViewById(R.id.img_logo);

        blink = AnimationUtils.loadAnimation(this,R.anim.blink);
        img_logo.setAnimation(blink);

        new Thread() {
            public void run()
            {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        }.start();
    }
}
