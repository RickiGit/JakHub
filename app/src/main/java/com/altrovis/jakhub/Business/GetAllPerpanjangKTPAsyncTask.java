package com.altrovis.jakhub.Business;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.altrovis.jakhub.Entities.GlobalVariable;

/**
 * Created by Wisnu on 22/02/2016.
 */
public class GetAllPerpanjangKTPAsyncTask extends AsyncTask<Void, Void, Void> {

    private String urlWebService = GlobalVariable.UrlGetAllPerpanjangKTP;
    private String urlComplete = "";

    private String param1 = "?NIK=";
    private String NIK;

    private ProgressDialog progressDialog;
    private Context context;
    private String errorMessage = "";

    public GetAllPerpanjangKTPAsyncTask(Context context, String NIK, String WaktuPelayanan) {

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
    protected Void doInBackground(Void... params) {

        try {
            GlobalVariable.listPerpanjangKTP = PerpanjangKTPHelper.getListOfPerpanjangKTP(urlComplete);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        if (errorMessage.length() > 0) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

}
