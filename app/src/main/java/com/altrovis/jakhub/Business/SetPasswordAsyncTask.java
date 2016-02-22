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
public class SetPasswordAsyncTask extends AsyncTask<Void, Void, JSONObject> {

    private String urlWebService = GlobalVariable.UrlSetPassword;
    private String urlComplete = "";

    private String param1 = "?NIK=";
    private String param2 = "&Password=";
    private String param3 = "&ActicationCode";

    private String NIK;
    private String Password;
    private String ActivationCode;

    private ProgressDialog progressDialog;
    private Context context;
    private String errorMessage = "";

    public SetPasswordAsyncTask(Context context, String NIK, String Password, String ActivationCode) {

        this.NIK = NIK;
        this.Password = Password;
        this.ActivationCode = ActivationCode;

        urlComplete = urlWebService.concat(param1).concat(this.NIK)
                .concat(param2).concat(this.Password).concat(param3).concat(this.ActivationCode);

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
                    Toast.makeText(context, "Password Berhasil Diubah", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, result.getString("ErrorMessage"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

}
