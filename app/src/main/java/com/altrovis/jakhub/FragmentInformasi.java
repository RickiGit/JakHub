package com.altrovis.jakhub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.altrovis.jakhub.Business.GridViewAdapter;
import com.altrovis.jakhub.Entities.ItemMenu;

import java.util.ArrayList;

public class FragmentInformasi extends Fragment {
    public FragmentInformasi() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informasi, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.GridViewMenu);
        final ArrayList<ItemMenu> listOfMenu = new ArrayList<ItemMenu>();
        listOfMenu.add(new ItemMenu(1, "Nomor Penting", R.drawable.icon_telepon_penting));
        listOfMenu.add(new ItemMenu(2, "Transjakarta", R.drawable.icon_transjakarta));
        listOfMenu.add(new ItemMenu(3, "Penduduk", R.drawable.icon_penduduk));
        listOfMenu.add(new ItemMenu(4, "Fasilitas Umum", R.drawable.icon_fasilitas_umum));
        listOfMenu.add(new ItemMenu(5, "CCTV", R.drawable.icon_cctv));
        GridViewAdapter adapter = new GridViewAdapter(getContext(), R.layout.grid_view_item_menu, listOfMenu);
        gridView.setAdapter(adapter);

        return view;
    }
}
