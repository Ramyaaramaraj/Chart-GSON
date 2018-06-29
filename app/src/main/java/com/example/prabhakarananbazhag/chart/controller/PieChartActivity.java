package com.example.prabhakarananbazhag.chart.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.prabhakarananbazhag.chart.model.PieChartData;
import com.example.prabhakarananbazhag.chart.R;
import com.example.prabhakarananbazhag.chart.view.PieChartView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;


public class PieChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart_activity);
        PieChartView mc = (PieChartView) findViewById(R.id.grap);
        mc.setdata(getjson());
        mc.start(getjson().getData().size());
    }

    public PieChartData getjson() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("Pie.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson=new Gson();
        PieChartData bar = gson.fromJson(json,PieChartData.class);
        return  bar;




    }
}
