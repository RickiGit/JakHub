package com.altrovis.jakhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.altrovis.jakhub.Business.GridViewAdapter;
import com.altrovis.jakhub.Entities.ItemMenu;

import java.util.ArrayList;

public class FragmentLayanan extends Fragment  {

    public FragmentLayanan() {
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
        View view = inflater.inflate(R.layout.fragment_layanan, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.GridViewMenu);
        final ArrayList<ItemMenu> listOfMenu = new ArrayList<ItemMenu>();
        listOfMenu.add(new ItemMenu(1, "KTP/KK", R.drawable.icon_ktp));
        listOfMenu.add(new ItemMenu(2, "Persuratan", R.drawable.icon_persuratan));
        GridViewAdapter adapter = new GridViewAdapter(getContext(), R.layout.grid_view_item_menu, listOfMenu);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listOfMenu.get(position).getID() == 1) {
                    Intent intent = new Intent(getActivity(), ActivityKK.class);
                    startActivity(intent);
                } else if (listOfMenu.get(position).getID() == 2) {

                }
            }
        });

        return view;
    }
}
