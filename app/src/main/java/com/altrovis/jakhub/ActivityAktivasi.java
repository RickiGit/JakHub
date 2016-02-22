package com.altrovis.jakhub;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAktivasi extends AppCompatActivity {

    EditText editTextNIK;
    Button buttonMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivasi);

        Intent intent = getIntent();
        String noNIK = intent.getExtras().getString("noNIK");

        inisialisasiLayout();
        editTextNIK.setText(noNIK);

        new AlertDialog.Builder(this)
                .setTitle("Kode Aktivasi")
                .setMessage("Kode Aktivasi Anda adalah 525")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();

        goToHome();
    }

    public void inisialisasiLayout()
    {
        editTextNIK = (EditText)findViewById(R.id.EditTextNIK);
        buttonMasuk = (Button)findViewById(R.id.ButtonMasuk);
    }

    public void goToHome()
    {
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
