package com.altrovis.jakhub.Business;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.altrovis.jakhub.Business.Notifikasi.NotifikasiAdapter;
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
    private FragmentActivity context;
    private String errorMessage = "";
    private NotifikasiAdapter adapter;

    public GetAllPerpanjangKTPAsyncTask(FragmentActivity context, String NIK, NotifikasiAdapter adapter) {

        this.NIK = NIK;
        urlComplete = urlWebService.concat(param1).concat(this.NIK);

        this.context = context;
        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.show();

        this.adapter = adapter;
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

        adapter.clear();
        adapter.addAll(GlobalVariable.listPerpanjangKTP);
        adapter.notifyDataSetChanged();
    }

}
