package com.altrovis.jakhub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityMain extends AppCompatActivity {

    Button buttonMasuk;
    TextView textViewDaftar, textViewNoKTP, textViewPIN;

    public int currentImageIndex = 0;
    Timer timer;
    TimerTask timerTask;

    ImageView slidingImage;

    private int[] Image = {
            R.drawable.background, R.drawable.background2, R.drawable.background3
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        inisialisasiLayout();
        goToLogin();
        goToDaftar();

        int delay = 7500; // delay muncul gambar
        int period = 7500; // ganti slide setiap / detik

        final Handler mHandler = new Handler();
        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {
                backgroundSlider();
            }
        };

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                mHandler.post(mUpdateResults);
            }

        }, delay, period);

    }

    private void backgroundSlider() {

        slidingImage = (ImageView) findViewById(R.id.ImageViewSlide);
        slidingImage.setImageResource(Image[currentImageIndex % Image.length]);

        currentImageIndex++;

        int fadeInDuration = 500; // Configure time values here
        int timeBetween = 6000;
        int fadeOutDuration = 1000;

        slidingImage.setVisibility(View.INVISIBLE);    //Visible or invisible by default - this will apply when the animation ends
        slidingImage.setImageResource(Image[currentImageIndex % Image.length]);

        currentImageIndex++;

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
        fadeIn.setDuration(fadeInDuration);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
        fadeOut.setStartOffset(fadeInDuration + timeBetween);
        fadeOut.setDuration(fadeOutDuration);

        AnimationSet animation = new AnimationSet(false); // change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        animation.setRepeatCount(Animation.INFINITE);
        slidingImage.startAnimation(animation);

    }

    public void inisialisasiLayout()
    {
        buttonMasuk = (Button) findViewById(R.id.ButtonMasuk);
        textViewDaftar = (TextView) findViewById(R.id.TextViewDaftar);
        textViewNoKTP = (TextView) findViewById(R.id.TextViewNoKTP);
        textViewPIN = (TextView) findViewById(R.id.TextViewPIN);
        slidingImage = (ImageView) findViewById(R.id.ImageViewSlide);
    }

    public void goToLogin()
    {
        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textViewPIN.getText().toString().equals("123456"))
                {
                    Toast.makeText(ActivityMain.this, "PIN yang anda masukan salah", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(ActivityMain.this, ActivityHome.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void goToDaftar()
    {
        textViewDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityDaftarAkun.class);
                startActivity(intent);
            }
        });
    }

    public void goToLupaKataSandi()
    {

    }
}
