package com.altrovis.jakhub.Business;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.altrovis.jakhub.Entities.ItemMenu;
import com.altrovis.jakhub.R;

import java.util.ArrayList;

/**
 * Created by ricki on 2/18/2016.
 */
public class GridViewAdapter extends ArrayAdapter<ItemMenu> {

    Context context;
    int resource;
    ArrayList<ItemMenu> listOfItemMenu;

    public GridViewAdapter(Context context, int resource, ArrayList<ItemMenu> listOfItemMenu) {
        super(context, resource, listOfItemMenu);

        this.context = context;
        this.resource = resource;
        this.listOfItemMenu = listOfItemMenu;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){

        if(view == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view =  inflater.inflate(resource, viewGroup,false);
        }

        ItemMenu itemMenu = listOfItemMenu.get(position);

        TextView textViewNamaMenu = (TextView) view.findViewById(R.id.TextViewNamaMenu);
        textViewNamaMenu.setText(itemMenu.getNama());

        ImageView imageViewMenuIcon = (ImageView) view.findViewById(R.id.ImageViewIconMenu);
        imageViewMenuIcon.setImageResource(itemMenu.getIconSource());

        return view;
    }
}
