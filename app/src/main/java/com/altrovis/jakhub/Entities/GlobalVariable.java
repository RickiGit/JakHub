package com.altrovis.jakhub.Entities;

import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Wisnu on 22/02/2016.
 */
public class GlobalVariable {

    public static String UrlAddPerpanjangKTP = "http://apps.dev.altrovis.com/JakHub/JakHubservice.asmx/AddPerpanjangKTP";
    public static String UrlGetAllPerpanjangKTP = "http://apps.dev.altrovis.com/JakHub/JakHubservice.asmx/GetAllPerpanjangKTP";

    public static String UrlAddUser = "http://apps.dev.altrovis.com/JakHub/JakHubservice.asmx/AddUser";
    public static String UrlGetUser = "http://apps.dev.altrovis.com/JakHub/JakHubservice.asmx/GetUser";
    public static String UrlSetPassword = "http://apps.dev.altrovis.com/JakHub/JakHubservice.asmx/SetPassword";

    public static ArrayList<PerpanjangKTP> listPerpanjangKTP = new ArrayList<PerpanjangKTP>();
    public static User user = new User();

    public static String tanggalPicker = "";
    public static EditText editTextTanggal;

}
