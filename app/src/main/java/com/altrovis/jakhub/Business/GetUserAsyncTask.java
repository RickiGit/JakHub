package com.altrovis.jakhub.Business;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.altrovis.jakhub.Entities.GlobalVariable;
import com.altrovis.jakhub.Entities.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wisnu on 22/02/2016.
 */
public class GetUserAsyncTask extends AsyncTask <Void, Void, JSONObject> {

    private String urlWebService = GlobalVariable.UrlGetUser;
    private String urlComplete = "";

    private String param1 = "?NIK=";
    private String NIK;

    private ProgressDialog progressDialog;
    private Context context;
    private String errorMessage = "";

    private static String TAG_ID = "ID";
    private static String TAG_NAMA = "Nama";
    private static String TAG_TEMPAT_LAHIR = "TempatLahir";
    private static String TAG_TANGGAL_LAHIR = "TanggalLahir";
    private static String TAG_ALAMAT = "Alamat";
    private static String TAG_NO_TELEPON = "NoTelepon";
    private static String TAG_NIK = "NIK";
    private static String TAG_NO_KK = "NoKK";
    private static String TAG_PASSWORD = "Password";

    public GetUserAsyncTask(Context context, String NIK) {

        this.NIK = NIK;
        urlComplete = urlWebService.concat(param1).concat(this.NIK);

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

            if(result != null){
                try {

                    User user = new User();
                    user.setID(result.getInt(TAG_ID));
                    user.setNama(result.getString(TAG_NAMA));
                    user.setTempatLahir(result.getString(TAG_TEMPAT_LAHIR));
                    user.setTanggalLahir(result.getString(TAG_TANGGAL_LAHIR));
                    user.setAlamat(result.getString(TAG_ALAMAT));
                    user.setNoTelepon(result.getString(TAG_NO_TELEPON));
                    user.setNIK(result.getString(TAG_NIK));
                    user.setNoKK(result.getString(TAG_NO_KK));
                    user.setPassword(result.getString(TAG_PASSWORD));
                    GlobalVariable.user = user;

                } catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
