package com.altrovis.jakhub;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class ActivityDaftarAkun extends AppCompatActivity {

    Button buttonDaftar;
    EditText editTextTanggalLahir, editTextNIK,
            editTextKK, editTextNoTelepon;
    Spinner spinnerJK;
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
        //setSpinnerJenisKelamin();
    }

    public void inisialisasiLayout()
    {
        buttonDaftar = (Button)findViewById(R.id.ButtonDaftar);
        editTextTanggalLahir = (EditText)findViewById(R.id.EditTextTanggalLahir);
        editTextNIK = (EditText)findViewById(R.id.EditTextNIK);
        editTextKK = (EditText) findViewById(R.id.EditTextKK);
        editTextNoTelepon = (EditText) findViewById(R.id.EditTextNoTelepon);
        //spinnerJK = (Spinner)findViewById(R.id.SpinnerJenisKelamin);
    }

    public void goToAktivasi()
    {
        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String noNIK = editTextNIK.getText().toString();
                String noKK = editTextKK.getText().toString();
                String tanggalLahir = editTextTanggalLahir.getText().toString();
                String noTelepon = editTextNoTelepon.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences(
                        "login", Context.MODE_PRIVATE).edit();
                editor.putString("nik", noNIK);
                editor.putString("nama", "Pevita");
                editor.putString("nik", noNIK);
                editor.putString("kk", noKK);
                editor.putString("tempatLahir","Parung Jawa Barat");
                editor.putString("tanggalLahir", tanggalLahir);
                editor.putString("alamat", "Jalan Mampang Prapatan XIV No .99 Mampang, Jakarta Selatan");
                editor.putString("noTelepon", noTelepon);
                editor.commit();

                final ProgressDialog progress = new ProgressDialog(v.getContext());
                progress.setMessage("Mengirim data...");
                progress.setCancelable(false);
                progress.setIndeterminate(true);
                progress.show();

                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        String noNIK = editTextNIK.getText().toString();
                        String noKK = editTextKK.getText().toString();
                        String tanggalLahir = editTextTanggalLahir.getText().toString();
                        String noTelepon = editTextNoTelepon.getText().toString();

                        SharedPreferences.Editor editor = getSharedPreferences(
                                "login", Context.MODE_PRIVATE).edit();
                        editor.putString("nik", noNIK);
                        editor.putString("nama", "Pevita");
                        editor.putString("kk", noKK);
                        editor.putString("tempatLahir","Parung Jawa Barat");
                        editor.putString("tanggalLahir", tanggalLahir);
                        editor.putString("alamat", "Jalan Mampang Prapatan XIV No .99 Mampang, Jakarta Selatan");
                        editor.putString("noTelepon", noTelepon);
                        editor.commit();

                        Intent intent = new Intent(ActivityDaftarAkun.this, ActivityAktivasi.class);
                        intent.putExtra("noNIK", noNIK);
                        startActivity(intent);

                        progress.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 2000);
            }
        });
    }

//    public void setSpinnerJenisKelamin()
//    {
//        String[] listSpinner = getResources().getStringArray(R.array.spinerJenisKelamin);
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerJK.setAdapter(dataAdapter);
//    }

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
