package com.altrovis.jakhub.Business;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.altrovis.jakhub.Entities.GlobalVariable;
import com.altrovis.jakhub.R;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Wisnu on 22/02/2016.
 */
public class AddPerpanjangKTPAsyncTask extends AsyncTask<Void, Void, JSONObject> {

    private String urlWebService = GlobalVariable.UrlAddPerpanjangKTP;
    private String urlComplete = "";

    private String param1 = "?NIK=";
    private String param2 = "&WaktuPelayanan=";

    private String NIK;
    private String WaktuPelayanan;

    private ProgressDialog progressDialog;
    private AppCompatActivity context;
    private String errorMessage = "";

    public AddPerpanjangKTPAsyncTask(AppCompatActivity context, String NIK, String WaktuPelayanan) {

        this.NIK = NIK;
        try {
            this.WaktuPelayanan = URLEncoder.encode(WaktuPelayanan, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        urlComplete = urlWebService.concat(param1).concat(this.NIK).concat(param2).concat(this.WaktuPelayanan);


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
            errorMessage = e.getMessage();
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

                FragmentManager fragmentManager = context.getSupportFragmentManager();
                while (fragmentManager.popBackStackImmediate()) {}
                RelativeLayout mainContainer = (RelativeLayout) context.findViewById(R.id.RelativeLayoutGridViewMenu);
                mainContainer.setVisibility(RelativeLayout.VISIBLE);

                if (isSuccessful) {
                    Toast.makeText(context, "Data Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, result.getString("ErrorMessage"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
