package com.altrovis.jakhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityMain extends AppCompatActivity {

    Button buttonMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        InisialisasiLayout();
        Login();
    }

    public void InisialisasiLayout()
    {
        buttonMasuk = (Button) findViewById(R.id.ButtonMasuk);
    }

    public void Login()
    {
        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityHome.class);
                startActivity(intent);
            }
        });
    }
}
