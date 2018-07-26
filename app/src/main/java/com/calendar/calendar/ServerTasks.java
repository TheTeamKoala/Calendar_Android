package com.calendar.calendar;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;



public class ServerTasks extends AsyncTask<String, String, String> {
    HttpURLConnection connection =null;
    BufferedReader br=null;
    ArrayList<Aktivite> list = new ArrayList<Aktivite>(10);
    public boolean conn=false;
    @Override
    protected String doInBackground(String... strings) {
        //ilk parametre ile server adresi alinir
        try {
            URL url = new URL("https://calendar-web-443.herokuapp.com/appointments.json");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String line;
            String file = "";
            while ((line = br.readLine()) != null) {
                Log.d("Line :", line);
                file += line;
            }

            return file;


        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            String result_json_text="",singleParsed="";
            // bu method ile doInBackGround methodunda serverdan elde ettigimiz bilgileri kullanacagiz
            JSONArray JA = new JSONArray(s);
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                Aktivite newest = new Aktivite();
                singleParsed =  "Date:" + JO.get("date") + "\n"+ "Text:" + JO.get("text") + "\n";
                try {
                    newest.setTarih(sdf.parse((String) JO.get("date")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                newest.setEtkinlik_adi((String) JO.get("text"));
                list.add(newest);
                result_json_text =  result_json_text + singleParsed +"\n" ;

            }
            Log.d("FOR_LOG", result_json_text);
            conn=true;
            MainActivity.aktivite_handler(list);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Aktivite> listOFAktivite(){
        return list ;
    }
}