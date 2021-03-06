package com.altrovis.jakhub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.altrovis.jakhub.Business.GridViewAdapter;
import com.altrovis.jakhub.Entities.ItemMenu;

import java.util.ArrayList;

public class FragmentLayanan extends Fragment  {

    public FragmentLayanan() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_layanan, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.GridViewMenu);
        final ArrayList<ItemMenu> listOfMenu = new ArrayList<ItemMenu>();
        listOfMenu.add(new ItemMenu(1, "KTP/KK", R.drawable.icon_ktp));
        listOfMenu.add(new ItemMenu(2, "Persuratan", R.drawable.icon_persuratan));
        listOfMenu.add(new ItemMenu(3, "Mutasi", R.drawable.icon_mutasi));
        listOfMenu.add(new ItemMenu(4, "Pelaporan", R.drawable.icon_pelaporan));
        listOfMenu.add(new ItemMenu(5, "Qlue", R.drawable.icon_clue));
        GridViewAdapter adapter = new GridViewAdapter(getContext(), R.layout.grid_view_item_menu, listOfMenu);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listOfMenu.get(position).getID() == 1) {

                    RelativeLayout mainContainer = (RelativeLayout) getActivity().findViewById(R.id.RelativeLayoutGridViewMenu);
                    mainContainer.setVisibility(RelativeLayout.GONE);

                    //tambahkan fragment ke fragment navigation backstack{
                    FragmentDomisiliKTP newFragment = new FragmentDomisiliKTP();

                    //untuk melakukan navigasi fragment, gunakan implementasi berikut ini:
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentDomisili, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (listOfMenu.get(position).getID() == 2) {

                }
            }
        });

        return view;
    }
}
