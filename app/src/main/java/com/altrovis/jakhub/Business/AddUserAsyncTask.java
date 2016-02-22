package com.altrovis.jakhub.Business;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.altrovis.jakhub.Entities.GlobalVariable;

import org.json.JSONObject;

/**
 * Created by Wisnu on 22/02/2016.
 */
public class AddUserAsyncTask extends AsyncTask <Void, Void, JSONObject> {

    private String urlWebService = GlobalVariable.UrlAddUser;
    private String urlComplete = "";

    private String param1 = "?Nama=";
    private String param2 = "&TempatLahir=";
    private String param3 = "&TanggalLahir=";
    private String param4 = "&Alamat=";
    private String param5 = "&NoTelepon=";
    private String param6 = "&NIK=";
    private String param7 = "&NoKK=";

    private String Nama;
    private String TempatLahir;
    private String TanggalLahir;
    private String Alamat;
    private String NoTelepon;
    private String NIK;
    private String NoKK;

    private ProgressDialog progressDialog;
    private Context context;
    private String errorMessage = "";


    public AddUserAsyncTask(Context context, String Nama, String TempatLahir, String
            TanggalLahir, String Alamat, String NoTelepon, String NIK, String NoKK) {

        this.Nama = Nama;
        this.TempatLahir = TempatLahir;
        this.TanggalLahir = TanggalLahir;
        this.Alamat = Alamat;
        this.NoTelepon = NoTelepon;
        this.NIK = NIK;
        this.NoKK = NoKK;

        urlComplete = urlWebService.concat(param1).concat(this.Nama).concat(param2).concat(this.TempatLahir)
                .concat(param3).concat(this.TanggalLahir).concat(param4).concat(this.Alamat).concat(param5).concat(NoTelepon)
                .concat(param6).concat(this.NIK).concat(param7).concat(this.NoKK);


        this.context = context;
        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.show();
    }


    protected void onPreExecute() {
        super.onPreExecute();

        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... params) {

        try {
            return GlobalFunction.GetJSONObject(urlComplete);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);

        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        if (errorMessage.length() > 0) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        } else {
            try {
                Boolean isSuccessful = result.getBoolean("IsSuccessful");
                if (isSuccessful) {
                    Toast.makeText(context, "User Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, result.getString("ErrorMessage"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

}
