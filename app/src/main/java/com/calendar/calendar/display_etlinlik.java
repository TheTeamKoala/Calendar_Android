package com.calendar.calendar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class display_etlinlik extends AppCompatActivity  {
    ListView list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_etlinlik);
        ArrayList<Aktivite> listOFAktivite =ServerTasks.listOFAktivite();
        list = (ListView) findViewById(R.id.list);
        ArrayAdapter<Aktivite> adapter = new ArrayAdapter<Aktivite>(this,
                android.R.layout.simple_list_item_1,listOFAktivite);
        list.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        View v = findViewById(android.R.id.content);
        send_akt(v);
    }

    private void send_akt(View v) {
        Intent intent;
        intent = new Intent(display_etlinlik.this, MainActivity.class);

        startActivity(intent);
        finish();
    }

}
