package com.calendar.calendar;

public class Aktivite {

    private String tarih="";
    private String saat="";
    private String etkinlik_adi="";
    private String olusturma_tarihi="";
    private String konum="";


    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public void setEtkinlik_adi(String etkinlik_adi) {
        this.etkinlik_adi = etkinlik_adi;
    }

    public void setOlusturma_tarihi(String olusturma_tarihi) {
        this.olusturma_tarihi = olusturma_tarihi;
    }

    public void setKonum(String konum) {
        this.konum = konum;
    }



    public String getSaat() {
        return saat;
    }

    public String getEtkinlik_adi() {
        return etkinlik_adi;
    }

    public String getKonum() {
        return konum;
    }

    public String getOlusturma_tarihi() {
        return olusturma_tarihi;
    }



    public String getTarih() {
        return tarih;
    }



}
