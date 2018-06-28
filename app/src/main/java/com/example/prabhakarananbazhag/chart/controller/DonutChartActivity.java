package com.example.prabhakarananbazhag.chart.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.prabhakarananbazhag.chart.R;
import com.example.prabhakarananbazhag.chart.model.DonutChartData;
import com.example.prabhakarananbazhag.chart.view.DonutChartView;
import com.google.gson.Gson;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class DonutChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_chart_activity);
        DonutChartView view = (DonutChartView) findViewById(R.id.donut_graph);
        view.setDonutGraphValues(getjson());
        view.start(getjson().getData().size());
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public float convertPxToDp(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public DonutChartData getjson()  {
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
        DonutChartData bar = gson.fromJson(json,DonutChartData.class);
        return bar;


    }
}
