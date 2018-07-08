package com.calendar.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Button bfecha,bhora;
    EditText efecha,ehora;
    private int day,month,year,hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //for calend
        bfecha =(Button) findViewById(R.id.bfecha);
        bhora =(Button) findViewById(R.id.bhora);
        efecha=(EditText)findViewById(R.id.efecha);
        ehora = (EditText)findViewById(R.id.ehora);

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

    public void aktivite_bitir(View view){
        Intent intent;
        intent = new Intent(Main2Activity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }
    public void send_akt(View view){
    Aktivite new_akt = new Aktivite();

    aktivite_bitir(view);
    }
}
