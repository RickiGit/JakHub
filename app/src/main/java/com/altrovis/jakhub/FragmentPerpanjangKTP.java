package com.altrovis.jakhub;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.altrovis.jakhub.Entities.GlobalVariable;

public class FragmentPerpanjangKTP extends Fragment {

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

        GlobalVariable.editTextTanggal = (EditText) view.findViewById(R.id.EditTextTanggal);
        GlobalVariable.editTextTanggal.setInputType(InputType.TYPE_NULL);

        GlobalVariable.editTextTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        return view;
    }
}
