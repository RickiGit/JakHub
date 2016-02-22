package com.altrovis.jakhub.Entities;

/**
 * Created by Wisnu on 22/02/2016.
 */
public class PerpanjangKTP {

    private int ID;
    private String NIK;
    private String WaktuPelayanan;
    private String NoReferensi;

    public PerpanjangKTP() {
    }

    public PerpanjangKTP(int ID, String NIK, String waktuPelayanan, String noReferensi) {
        this.ID = ID;
        this.NIK = NIK;
        WaktuPelayanan = waktuPelayanan;
        NoReferensi = noReferensi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getWaktuPelayanan() {
        return WaktuPelayanan;
    }

    public void setWaktuPelayanan(String waktuPelayanan) {
        WaktuPelayanan = waktuPelayanan;
    }

    public String getNoReferensi() {
        return NoReferensi;
    }

    public void setNoReferensi(String noReferensi) {
        NoReferensi = noReferensi;
    }
}
