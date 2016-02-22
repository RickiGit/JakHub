package com.altrovis.jakhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity {

    Button buttonMasuk;
    TextView textViewDaftar;

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
    }

    public void inisialisasiLayout()
    {
        buttonMasuk = (Button) findViewById(R.id.ButtonMasuk);
        textViewDaftar = (TextView) findViewById(R.id.TextViewDaftar);
    }

    public void goToLogin()
    {
        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityHome.class);
                startActivity(intent);
                finish();
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
