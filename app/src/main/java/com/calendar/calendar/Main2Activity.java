package com.calendar.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.HttpHeaderParser;
import java.io.IOException;
import java.util.Calendar;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Button bfecha,bhora;
    EditText efecha,ehora,etk_adi;
    private int day,month,year,hour,minute;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        View v = findViewById(android.R.id.content);
        send_akt(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //for calend
        bfecha =(Button) findViewById(R.id.bfecha);
        bhora =(Button) findViewById(R.id.bhora);
        efecha=(EditText)findViewById(R.id.efecha);
        ehora = (EditText)findViewById(R.id.ehora);
        etk_adi = (EditText)findViewById(R.id.etk_adi);



        bfecha.setOnClickListener(this);
        bhora.setOnClickListener((View.OnClickListener) this);

    }

    @Override// for calend
    public void onClick(View v) {

        if(v == bfecha){
            final Calendar c =Calendar.getInstance();
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efecha.setText(dayOfMonth + "/" +(month+1)+"/"+year);

                }
            } ,day,month,year);
            datePickerDialog.show();


        }
        if(v== bhora){

            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay+":" + minute);

                }
            },hour,minute,false);
            timePickerDialog.show();
        }

    }
    public void aktivite_bitir1(View view) throws ParseException {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
       final Context mContext=getApplicationContext();
        Aktivite akt = new Aktivite();
        Date d = sdf.parse("2018-08-11T17:51:00.000Z");
        akt.setTarih(d);
        akt.setEtkinlik_adi(etk_adi.toString());

        JSONObject jso = new JSONObject();
        try {
            jso.put("text",akt.getEtkinlik_adi());
            jso.put("date","2018-08-10T17:51:00.000Z");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "https://calendar-web-443.herokuapp.com/appointments";
        RequestQueue queue = Volley.newRequestQueue(this);
        int requestType=Request.Method.POST;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestType, url,jso,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(mContext,"Appointment basariyla olusturuldu:"+response.toString(),Toast.LENGTH_SHORT);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext,"HATA:Appointment olusturulamadi:"+error.toString(),Toast.LENGTH_SHORT);
            }
        });
        Log.d("Line :","yunus emre");

        queue.add(jsonObjectRequest);
    }
    public void aktivite_bitir(View view) throws ParseException {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        Aktivite akt = new Aktivite();
        Date d = sdf.parse("2018-08-11T17:51:00.000Z");
        akt.setTarih(d);
        akt.setEtkinlik_adi(etk_adi.toString());
        HttpsURLConnection conn = null, connPut = null;
        try {
            URL webServerUrl = new URL("https://calendar-web-443.herokuapp.com/appointments");
                connPut =(HttpsURLConnection) webServerUrl.openConnection();

                // Activity wants us to push some appointments to web-server
                connPut.setReadTimeout(10000 /* milliseconds */);
                connPut.setConnectTimeout(15000 /* milliseconds */);
                connPut.setDoOutput(true);

                connPut.setRequestMethod("POST");
                connPut.setRequestProperty("Content-type", "application/json");
            Log.d("Line :", connPut.getContentType().toString());
                connPut.connect();
                OutputStreamWriter out = new OutputStreamWriter(connPut.getOutputStream());

                 out.write(akt.parseJSONObject().toString());
                 out.flush();


                out.close();
                if(connPut.getResponseCode() == 200)
                    System.out.println("Successfully posted a new apt.");
                else
                    System.out.println(connPut.getResponseMessage());

                connPut.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(conn!=null)
                conn.disconnect();
            if(connPut!=null)
                connPut.disconnect();
        }

        View v =  findViewById(android.R.id.content);
        send_akt(v);
    }
    public void send_akt(View view){
        Intent intent;
        intent = new Intent(Main2Activity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }
}
