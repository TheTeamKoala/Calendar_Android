package com.calendar.calendar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Aktivite {

    private Date tarih = null ;
    private String etkinlik_adi="";



    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }



    public void setEtkinlik_adi(String etkinlik_adi) {
        this.etkinlik_adi = etkinlik_adi;
    }




    public String getEtkinlik_adi() {
        return etkinlik_adi;
    }


    public Date getTarih() {
        return tarih;
    }


    public JSONObject parseJSONObject() {
        JSONObject jso = new JSONObject();
        try {
            jso.put("text",this.etkinlik_adi);
            jso.put("date","2018-08-10T17:51:00.000Z");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jso;
    }
}
