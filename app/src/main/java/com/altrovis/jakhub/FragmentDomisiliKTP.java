package com.altrovis.jakhub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentDomisiliKTP extends Fragment {


    public FragmentDomisiliKTP() {
        // Required empty public constructor
    }

    public static FragmentDomisiliKTP newInstance(String param1, String param2) {
        FragmentDomisiliKTP fragment = new FragmentDomisiliKTP();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_domisili_ktp, container, false);

        this.SetTextView(rootView);

        Spinner spinnerLayanan = (Spinner) rootView.findViewById(R.id.SpinnerKTP);

        String[] listSpinner = getResources().getStringArray(R.array.spinnerKTP);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLayanan.setAdapter(dataAdapter);

        spinnerLayanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if(position == 1)
                        {
                            FragmentPerpanjangKTP newFragment = new FragmentPerpanjangKTP();

                            //untuk melakukan navigasi fragment, gunakan implementasi berikut ini:
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.LinearLayoutFragmentDomisili, newFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        return rootView;
    }

    private void SetTextView(View rootView){

        TextView textViewNamaLengkap = (TextView) rootView.findViewById(R.id.TextViewNamaLengkap);
        TextView textViewTempatLahir = (TextView) rootView.findViewById(R.id.TextViewTempatLahir);
        TextView textViewTanggalLahir = (TextView) rootView.findViewById(R.id.TextViewTanggalLahir);
        TextView textViewAlamat = (TextView) rootView.findViewById(R.id.TextViewAlamat);
        TextView textViewNoTelepon = (TextView) rootView.findViewById(R.id.TextViewNoTelepon);

        SharedPreferences login = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
        textViewNamaLengkap.setText(login.getString("nama",""));
        textViewTempatLahir.setText(login.getString("tempatLahir",""));
        textViewTanggalLahir.setText(login.getString("tanggalLahir",""));
        textViewAlamat.setText(login.getString("alamat",""));
        textViewNoTelepon.setText(login.getString("noTelepon",""));

    }
}
