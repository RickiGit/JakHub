package com.altrovis.jakhub.Business.Notifikasi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.altrovis.jakhub.Entities.PerpanjangKTP;
import com.altrovis.jakhub.R;

import java.util.ArrayList;

/**
 * Created by ricki on 2/22/2016.
 */
public class NotifikasiAdapter extends ArrayAdapter<PerpanjangKTP> {

    Context context;
    int resource;
    ArrayList<PerpanjangKTP> listOfPerpanjangKTP;

    public NotifikasiAdapter(Context context, int resource, ArrayList<PerpanjangKTP> listOfPerpanjangKTP) {
        super(context, resource,listOfPerpanjangKTP);

        this.context = context;
        this.resource = resource;
        this.listOfPerpanjangKTP = listOfPerpanjangKTP;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resource, viewGroup, false);
        }

        PerpanjangKTP perpanjangKTP = listOfPerpanjangKTP.get(position);

        TextView textViewJudul = (TextView) view.findViewById(R.id.TextViewJudul);
        textViewJudul.setText("Konfirmasi KTP Perpanjangan \nKTP : " + perpanjangKTP.getNoReferensi());

        TextView textViewTanggal = (TextView) view.findViewById(R.id.TextViewTanggal);
        textViewTanggal.setText(perpanjangKTP.getWaktuPelayanan());

        return view;
    }

}
