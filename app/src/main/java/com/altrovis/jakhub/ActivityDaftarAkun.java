package com.altrovis.jakhub;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ActivityDaftarAkun extends AppCompatActivity {

    Button buttonDaftar;
    EditText editTextTanggalLahir, editTextNIK,
            editTextNamaLengkap, editTextNoTelepon;
    int years, month, day;
    static final int DIALOG_TANGGAL_LAHIR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Calendar calender = Calendar.getInstance();
        years = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);

        inisialisasiLayout();
        goToAktivasi();
        showDialogTanggalLahir();
    }

    public void inisialisasiLayout()
    {
        buttonDaftar = (Button)findViewById(R.id.ButtonDaftar);
        editTextTanggalLahir = (EditText)findViewById(R.id.EditTextTanggalLahir);
        editTextNIK = (EditText)findViewById(R.id.EditTextNIK);
        editTextNamaLengkap = (EditText) findViewById(R.id.EditTextNamaLengkap);
        editTextNoTelepon = (EditText) findViewById(R.id.EditTextNoTelepon);
    }

    public void goToAktivasi()
    {
        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noNIK = editTextNIK.getText().toString();
                String namaLengkap = editTextNamaLengkap.getText().toString();
                String tanggalLahir = editTextTanggalLahir.getText().toString();
                String noTelepon = editTextNoTelepon.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences(
                        "login", Context.MODE_PRIVATE).edit();
                editor.putString("nik", noNIK);
                editor.putString("nama", namaLengkap);
                editor.putString("tanggalLahir", tanggalLahir);
                editor.putString("noTelepon", noTelepon);
                editor.commit();

                Intent intent = new Intent(ActivityDaftarAkun.this, ActivityAktivasi.class);
                intent.putExtra("noNIK", noNIK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TANGGAL_LAHIR) {
            return new DatePickerDialog(this, datePickerTanggalLahir, years, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerTanggalLahir = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            years = year;
            month = monthOfYear + 1;
            day = dayOfMonth;

            editTextTanggalLahir.setText(day + "/" + month + "/" + year);
        }
    };

    public void showDialogTanggalLahir()
    {
        editTextTanggalLahir.setInputType(InputType.TYPE_NULL);
        editTextTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_TANGGAL_LAHIR);
            }
        });
    }
}
