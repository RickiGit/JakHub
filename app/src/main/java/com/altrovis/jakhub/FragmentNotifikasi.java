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
import android.widget.ListView;

import com.altrovis.jakhub.Business.GetAllPerpanjangKTPAsyncTask;
import com.altrovis.jakhub.Business.Notifikasi.NotifikasiAdapter;
import com.altrovis.jakhub.Entities.GlobalVariable;


public class FragmentNotifikasi extends Fragment {

    ListView listViewNotifikasi;
    NotifikasiAdapter adapter;

    public FragmentNotifikasi() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifikasi, container, false);

        listViewNotifikasi = (ListView) view.findViewById(R.id.ListVieNotifikasi);
        adapter = new NotifikasiAdapter(getActivity(), R.layout.item_listview_notifikasi,
                GlobalVariable.listPerpanjangKTP);
        listViewNotifikasi.setAdapter(adapter);

        listViewNotifikasi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GlobalVariable.selectedPerpanjangKTP = GlobalVariable.listPerpanjangKTP.get(position);

                FragmentPerpanjangKTP newFragment = new FragmentPerpanjangKTP();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.LinearLayoutFragmentNotifikasi, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        SharedPreferences login = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
        String nik = login.getString("nik","");

        new GetAllPerpanjangKTPAsyncTask(getActivity(), nik, adapter).execute();



        return view;
    }
}
