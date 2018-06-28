package com.example.prabhakarananbazhag.chart;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prabhakarananbazhag.chart.controller.BarChartActivity;
import com.example.prabhakarananbazhag.chart.controller.DonutChartActivity;
import com.example.prabhakarananbazhag.chart.controller.LineChartActivity;
import com.example.prabhakarananbazhag.chart.controller.PieChartActivity;
import com.example.prabhakarananbazhag.chart.controller.ScatterChartActivity;

public class MainActivity extends AppCompatActivity {
Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BarChartActivity.class);
               startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),DonutChartActivity.class);
                startActivity(j);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getApplicationContext(),LineChartActivity.class);
                startActivity(k);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(getApplicationContext(),PieChartActivity.class);
                startActivity(l);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getApplicationContext(),ScatterChartActivity.class);
                startActivity(m);
            }
        });




    }
}
