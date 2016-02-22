package com.altrovis.jakhub;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAktivasi extends AppCompatActivity {

    EditText editTextNIK;
    EditText editTextKodeAktivasi;
    Button buttonMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivasi);

        Intent intent = getIntent();
        String noNIK = intent.getExtras().getString("noNIK");

        inisialisasiLayout();
        editTextNIK.setText(noNIK);

        final Context activityContext = this;

        final ProgressDialog progress = new ProgressDialog(activityContext);
        progress.setMessage("Menunggu kode aktivasi...");
        progress.setCancelable(false);
        progress.setIndeterminate(true);
        progress.show();


        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {

                new AlertDialog.Builder(activityContext)
                        .setTitle("Kode Aktivasi")
                        .setMessage("Kode aktivasi Anda adalah 5251")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

                editTextKodeAktivasi.setText("5251");

                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 2000);

        goToHome();
    }

    public void inisialisasiLayout() {
        editTextNIK = (EditText) findViewById(R.id.EditTextNIK);
        editTextKodeAktivasi = (EditText) findViewById(R.id.EditTextKodeAktivasi);
        buttonMasuk = (Button) findViewById(R.id.ButtonMasuk);
    }

    public void goToHome() {
        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAktivasi.this, ActivityHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
