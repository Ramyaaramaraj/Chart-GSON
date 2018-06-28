package com.example.prabhakarananbazhag.chart.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.prabhakarananbazhag.chart.model.ScatterChartData;
import com.example.prabhakarananbazhag.chart.R;
import com.example.prabhakarananbazhag.chart.view.ScatterChartView;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class ScatterChartActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scatter_chart_activity);
        ScatterChartView dl = (ScatterChartView) findViewById(R.id.scatter);
        try {
            dl.setvalues(getjson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ScatterChartData getjson() throws JSONException {
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
        ScatterChartData scatter = gson.fromJson(json,ScatterChartData.class);
        return  scatter;
    }
}
