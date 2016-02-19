package com.altrovis.jakhub.Entities;

/**
 * Created by ricki on 2/18/2016.
 */
public class ItemMenu {

    private int ID;
    private String Nama;
    private int IconSource;

    public ItemMenu(int ID, String nama, int iconSource) {
        this.ID = ID;
        Nama = nama;
        IconSource = iconSource;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public int getIconSource() {
        return IconSource;
    }

    public void setIconSource(int iconSource) {
        IconSource = iconSource;
    }
}