package com.altrovis.jakhub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.altrovis.jakhub.Entities.GlobalVariable;
import com.altrovis.jakhub.Entities.PerpanjangKTP;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FragmentDetailNotifikasi extends Fragment {

    public FragmentDetailNotifikasi() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_notifikasi, container, false);
        SetTextView(view);
        return view;
    }

    private void SetTextView(View view){

        TextView textViewJudul = (TextView) view.findViewById(R.id.TextViewJudul);
        TextView textViewTanggal = (TextView) view.findViewById(R.id.TextViewTanggal);
        TextView textViewNoReferensi = (TextView) view.findViewById(R.id.TextViewNoReferensi);
        TextView textViewPeta = (TextView) view.findViewById(R.id.TextViewMap);

        String judul = "Konfirmasi Perpanjangan KTP ID ";
        PerpanjangKTP perpanjangKTP = GlobalVariable.selectedPerpanjangKTP;

        textViewJudul.setText(judul + perpanjangKTP.getNoReferensi());
        textViewNoReferensi.setText(perpanjangKTP.getNoReferensi());

        String timestamp = perpanjangKTP.getWaktuPelayanan().replace("/Date(", "").replace(")/", "");
        Date created = new Date(Long.parseLong(timestamp));

        DateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
        String tanggal = formatter.format(created);
        textViewTanggal.setText(tanggal);

        textViewPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                        "&daddr=" + -6.2489461 + "," + 106.8256766));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

    }


}
