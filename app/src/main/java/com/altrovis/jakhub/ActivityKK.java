package com.altrovis.jakhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class ActivityKK extends AppCompatActivity {

    Spinner spinnerKTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kk);

        InisialisasiLayout();
        setIntentSpinner();
    }

    public void InisialisasiLayout()
    {
        spinnerKTP = (Spinner) findViewById(R.id.SpinnerKTP);
    }

    public void setIntentSpinner()
    {
        spinnerKTP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityKK.this, ActivityPerpanjangKTP.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
