package com.altrovis.jakhub.Entities;

/**
 * Created by Wisnu on 22/02/2016.
 */
public class User {

    private int ID;
    private String Nama;
    private String TempatLahir;
    private String TanggalLahir;
    private String Alamat;
    private String NoTelepon;
    private String NIK;
    private String NoKK;
    private String Password;

    public User() {
    }

    public User(int ID, String nama, String tempatLahir, String tanggalLahir, String alamat,
                String noTelepon, String NIK, String noKK, String password) {
        this.ID = ID;
        Nama = nama;
        TempatLahir = tempatLahir;
        TanggalLahir = tanggalLahir;
        Alamat = alamat;
        NoTelepon = noTelepon;
        this.NIK = NIK;
        NoKK = noKK;
        Password = password;
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

    public String getTempatLahir() {
        return TempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        TempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return TanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        TanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getNoTelepon() {
        return NoTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        NoTelepon = noTelepon;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getNoKK() {
        return NoKK;
    }

    public void setNoKK(String noKK) {
        NoKK = noKK;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
