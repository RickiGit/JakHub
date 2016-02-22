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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

        String timestamp = perpanjangKTP.getWaktuPelayanan().replace("/Date(", "").replace(")/", "");
        Date created = new Date(Long.parseLong(timestamp));

        DateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
        String tanggal = formatter.format(created);
        textViewTanggal.setText(tanggal);

        return view;
    }

}
