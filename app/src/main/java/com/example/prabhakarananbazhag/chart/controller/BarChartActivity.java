package com.example.prabhakarananbazhag.chart.controller;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.prabhakarananbazhag.chart.model.BarChartData;
import com.example.prabhakarananbazhag.chart.R;
import com.example.prabhakarananbazhag.chart.view.BarChartView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class BarChartActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_chart_activity);
        BarChartView dl = (BarChartView) findViewById(R.id.bar);
        try {
            dl.setvalues(getjson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public BarChartData getjson() throws JSONException {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("Bar.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson=new Gson();
        BarChartData bar = gson.fromJson(json,BarChartData.class);
        return  bar;
    }
}
