package com.altrovis.jakhub;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class FragmentPerpanjangKTP extends Fragment {

    int years, month, day;
    static final int DIALOG_TANGGAL = 0;
    EditText editTextTanggal;

    public FragmentPerpanjangKTP() {
        // Required empty public constructor
    }

    public static FragmentPerpanjangKTP newInstance(String param1, String param2) {
        FragmentPerpanjangKTP fragment = new FragmentPerpanjangKTP();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_perpanjang_ktp, container, false);

        Spinner spinnerLayanan = (Spinner) view.findViewById(R.id.SpinnerKTP);

        String[] listSpinner = getResources().getStringArray(R.array.spinnerKTP);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLayanan.setAdapter(dataAdapter);

        spinnerLayanan.setSelection(1);

        spinnerLayanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    FragmentDomisiliKTP newFragment = new FragmentDomisiliKTP();

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

        Calendar calender = Calendar.getInstance();
        years = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);

        editTextTanggal = (EditText) getActivity().findViewById(R.id.EditTextTanggal);

        return view;
    }

    private DatePickerDialog.OnDateSetListener datePickerTanggalDatang = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            years = year;
            month = monthOfYear + 1;
            day = dayOfMonth;

            editTextTanggal.setText(day + "/" + month + "/" + year);
        }
    };
}
