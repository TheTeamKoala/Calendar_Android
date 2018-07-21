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
import java.time.Clock;

public class ServerTasks extends AsyncTask<String, String, String> {
    HttpURLConnection connection =null;
    BufferedReader br=null;

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


        try {
            String result_json_text="",singleParsed="";
            // bu method ile doInBackGround methodunda serverdan elde ettigimiz bilgileri kullanacagiz
            JSONArray JA = new JSONArray(s);
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  "Date:" + JO.get("date") + "\n"+ "Text:" + JO.get("text") + "\n";

                result_json_text =  result_json_text + singleParsed +"\n" ;


            }
            Log.d("FOR_LOG", result_json_text);




        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}