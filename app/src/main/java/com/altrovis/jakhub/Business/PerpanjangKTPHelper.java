package com.altrovis.jakhub.Business;

import com.altrovis.jakhub.Entities.PerpanjangKTP;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Wisnu on 22/02/2016.
 */
public class PerpanjangKTPHelper {

    private static String TAG_ID = "ID";
    private static String TAG_NIK = "NIK";
    private static String TAG_WAKTU_PELAYANAN = "WaktuPelayanan";
    private static String TAG_NO_REFERENSI = "NoReferensi";

    public static ArrayList<PerpanjangKTP> getListOfPerpanjangKTP(String url) throws Exception {

        JSONArray arrayOfPerpanjangKTP = GlobalFunction.GetJSONArray(url);
        ArrayList<PerpanjangKTP> listOfPerpanjangKTP =  new ArrayList<PerpanjangKTP>();

        if (arrayOfPerpanjangKTP != null) {
            if (arrayOfPerpanjangKTP.length() > 0) {
                for (int j = 0; j < arrayOfPerpanjangKTP.length(); j++) {
                    JSONObject detailPerpanjangKTP = arrayOfPerpanjangKTP.getJSONObject(j);

                    PerpanjangKTP perpanjangKTP = new PerpanjangKTP();
                    perpanjangKTP.setID(detailPerpanjangKTP.getInt(TAG_ID));
                    perpanjangKTP.setNIK(detailPerpanjangKTP.getString(TAG_NIK));
                    perpanjangKTP.setWaktuPelayanan(detailPerpanjangKTP.getString(TAG_WAKTU_PELAYANAN));
                    perpanjangKTP.setNoReferensi(detailPerpanjangKTP.getString(TAG_NO_REFERENSI));
                    listOfPerpanjangKTP.add(perpanjangKTP);
                }
            }
        }

        return listOfPerpanjangKTP;
    }


}
