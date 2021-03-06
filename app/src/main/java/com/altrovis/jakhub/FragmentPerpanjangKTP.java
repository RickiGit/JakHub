package com.altrovis.jakhub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.altrovis.jakhub.Entities.GlobalVariable;

public class FragmentPerpanjangKTP extends Fragment {

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

        this.SetTextView(view);

        Spinner spinnerLayanan = (Spinner) view.findViewById(R.id.SpinnerKTP);

        String[] listSpinner = getResources().getStringArray(R.array.spinnerKTP);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLayanan.setAdapter(dataAdapter);

        spinnerLayanan.setSelection(1);

        spinnerLayanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        GlobalVariable.editTextTanggal = (EditText) view.findViewById(R.id.EditTextTanggal);
        GlobalVariable.editTextTanggal.setInputType(InputType.TYPE_NULL);

        GlobalVariable.editTextTanggal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getFragmentManager(), "DatePicker");
                }

                return false;
            }
        });

        return view;
    }

    private void SetTextView(View rootView) {

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
