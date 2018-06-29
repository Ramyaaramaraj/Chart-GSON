package com.example.prabhakarananbazhag.chart.view;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.prabhakarananbazhag.chart.model.BarChartData;
import com.example.prabhakarananbazhag.chart.model.LineChartData;
import com.example.prabhakarananbazhag.chart.model.ScatterChartData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

public class ScatterChartView extends ChartView{
    int no_of_iteration=0;
    public ValueAnimator mTimerAnimator;
    Paint lines=new Paint();
    Paint plot = new Paint();
    Paint scale = new Paint();
    Paint coordinate_point = new Paint();
    Paint labels = new Paint();
    Paint paint=new Paint();

    public ScatterChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public ScatterChartView(Context context) {
        super(context);

    }
    ScatterChartData cvalues;
    public  void setvalues(ScatterChartData cd) {
        cvalues =cd;
        postInvalidate();
    }
    @Override
    public void onDraw(Canvas canvas) {
        if (cvalues != null) {
            //.........................Canvas Attributes..................
            int length = canvas.getHeight();
            int breadth = canvas.getWidth();
            int len_dec = length / 10;
            int bre_dec = breadth / 10;
            int dec;
            if(len_dec>bre_dec)
            {
                dec=len_dec;
            }
            else if(bre_dec>len_dec)
            {
                dec=bre_dec;
            }
            else
            {
                dec=bre_dec;
            }
            Log.i("String dec", String.valueOf(dec));

            //..................Colours................
            ArrayList Colours=new ArrayList();
            for(ScatterChartData.Plot c:cvalues.getPlot()){
                Colours.add(c.getColor());
            }

            //..................Labels................
            ArrayList Labels=new ArrayList();
            for( ScatterChartData.Label l:cvalues.getLabel()) {
                Labels.add(l.getX());
                Labels.add(l.getY());
            }
           /* lines.setColor(Color.parseColor((String)Colours.get(0)));
            coordinate_point.setColor(Color.parseColor((String)Colours.get(1)));
            plot.setColor(Color.parseColor((String)Colours.get(2)));
            scale.setColor(Color.parseColor((String)Colours.get(3)));
            labels.setColor(Color.parseColor((String)Colours.get(4)));*/

            lines.setColor(Color.BLACK);
            coordinate_point.setColor(Color.GRAY);
            plot.setColor(Color.MAGENTA);
            scale.setColor(Color.RED);
            labels.setColor(Color.BLACK);
            //.................Label Printing.............//
            int size=getWidth()/30;
            int size1=dec/4;
          //  plot.setTextAlign(Paint.Align.CENTER);
            StringBuffer heading =new StringBuffer();
            heading.append(Labels.get(0)+" vs "+Labels.get(1));
            //...............Resizing the txt...............//
            float heading_size=labels.measureText(String.valueOf(heading));
            int text_center= (int) (heading_size/2);
            int canvas_center=breadth/2;
            int txt_start=canvas_center-text_center;
            labels.setTextSize(dec/2);
            canvas.drawText(String.valueOf(heading), txt_start, (dec/2), labels);
            labels.setTextSize(dec/3);
            canvas.drawText((String) Labels.get(0), breadth/2, (dec/2)+(dec/3), labels);
            Path path = new Path();
            path.moveTo(breadth-(dec-(dec/2)),length/2);
            path.lineTo(breadth-(dec-(dec/2)),length);
            canvas.drawPath(path,labels);
            canvas.drawTextOnPath((String) Labels.get(1),path,0,0,labels);
            //...............Rectangle Creation..............
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(dec, dec, breadth-dec, length-dec,paint);
            //.............Xarray and Yarray Creation................
            ArrayList Xaxis=new ArrayList();
            ArrayList Yaxis=new ArrayList();
            //............Fetching Values Using For Each...................//
            for( BarChartData.Plot x:cvalues.getPlot())
            {
                Xaxis.add(x.getX());
            }

            for( BarChartData.Plot y:cvalues.getPlot())
            {
                Yaxis.add(y.getY());
            }
            //.............XFormat Checking.............
            String xcheck= String.valueOf(Xaxis.get(0));
            float q=paint.measureText(String.valueOf(xcheck));
            Log.i("St", String.valueOf(q));
            int check=xFormat(xcheck);
            int xc=0;
            HashMap xplot=new HashMap();
            switch (check)
            {
                case 0:
                    xplot=xString(Xaxis,canvas,length,breadth,dec);
                    xc=1;
                    break;
                default:
                    Log.i("scale","Integer");
                    xplot=xNumber(Xaxis,canvas,length,breadth,dec);
                    xc=2;
                    break;
            }
            //.............YFormat Checking.............
            String ycheck= String.valueOf(Yaxis.get(0));
            int checky=yFormat(ycheck);
            int yc=0;
            HashMap yplot=new HashMap();
            switch (checky)
            {
                case 0:
                    yplot=yString(Yaxis,canvas,length,breadth,dec);
                    yc=1;
                    break;
                default:
                    Log.i("scale","y...Integer");
                    yplot= yNumber(Yaxis,canvas,length,breadth,dec);
                    yc=2;
                    break;
            }
            //.........PLOTTING..............
            plot(Xaxis,Yaxis,xplot,yplot,canvas,xc,yc,dec);
        } else {
            return;
        }
    }
    private void plot(ArrayList xaxis, ArrayList yaxis,  HashMap xplot, HashMap yplot, Canvas canvas, int xc, int yc,int dec) {
        int s=xaxis.size();
        /*int xsplit=((getWidth()-dec)-dec)/xaxis.size();
        int xsize[]=new int[xaxis.size()];
        for(int i=0;i<xaxis.size();i++)
        {
            axis.setTextSize(130);
            while(axis.measureText(String.valueOf(xaxis.get(i)))>xsplit){
                axis.setTextSize(axis.getTextSize()-1);
            }
            xsize[i]= (int) axis.getTextSize();
        }
        Arrays.sort(xsize);
        int x_value = xsize[xaxis.size()/2];
        axis.setTextSize(x_value/2);*/
        if((xc==1)&&(yc==1)) { //X and Y String
            for (int j = 0; j < s; j++) {
                String val1=(String) xaxis.get(j);
                String val2=(String) yaxis.get(j);
                Object xcc =  xplot.get(val1);
                Object ycc =  yplot.get(val2);
                StringBuffer label=new StringBuffer();
                label.append("("+xaxis.get(j)+","+yaxis.get(j)+")");
                canvas.drawText(String.valueOf(label), (int)xcc-10, (int)ycc-10, labels);
                canvas.drawCircle((int) xcc, (int) ycc, 8, plot);
            }
        }
        if((xc==1)&&(yc==2)) {  //X String Y Float
            for (int j = 0; j < s; j++) {
                String val2=(String) yaxis.get(j);
                float tc=Float.parseFloat(val2);
                //Check wheather the number is Integer or Float..........
                if((yplot.containsKey(tc))) {
                    float new_value=  Float.parseFloat((String)yaxis.get(j));
                    String val1= (String) xaxis.get(j);
                    Object ycc =  yplot.get(new_value);
                    Object xcc =  xplot.get(val1);
                    StringBuffer label=new StringBuffer();
                    label.append("("+xaxis.get(j)+","+yaxis.get(j)+")");
                    canvas.drawText(String.valueOf(label), (int)xcc-10, (int)ycc-10, labels);
                    canvas.drawCircle((int) xcc, (int) ycc, 8, plot);
                }
                else {
                    float val;
                    //...............Float Logic.....
                    float xcc =Float.parseFloat((String) yaxis.get(j));
                    //...........Integer part separation........
                    int integer_part= (int) xcc;
                    //........Decimal part separation........
                    String dot=".";
                    int count=0;
                    for(int d=0;d<val2.length();d++) {
                        if(String.valueOf(val2.charAt(d)).equals(dot)) {
                            ++count;
                            break;
                        }
                    }
                    String decimal=val2.substring(count+1);
                    float decimal_part=Float.parseFloat((decimal));
                    //...........Distance between two Elements.................
                    int distance;
                    Object temp1=yplot.get((float)integer_part);
                    Object temp2;
                    int v=s-1;
                    if(j==v) {
                        temp2=yplot.get((float)integer_part-1);
                    }
                    else {
                        temp2=yplot.get((float)integer_part+1);
                    }
                    distance=(int)temp2-(int)temp1;
                    //.................Internal Distance Calculation..............
                    float internal_distance=(float)distance/100;
                    //..........New Pixel......
                    float pixel_new= (float)((float)internal_distance*decimal_part) ;
                    val=(int)temp1+pixel_new;
                    String val1= (String) xaxis.get(j);
                    Object xcc_f =  xplot.get(val1);
                    StringBuffer label=new StringBuffer();
                    label.append("("+xaxis.get(j)+","+yaxis.get(j)+")");
                    canvas.drawText(String.valueOf(label), (int)xcc_f-10, (int)val-10, labels);
                    canvas.drawCircle((int)xcc_f,(int) val, 8, plot);
                }
            }
        }
        if((xc==2)&&(yc==1)) {   //X Number....Y String....//
            for (int j = 0; j < s; j++) {
                //int val1=Integer.parseInt ((String) xaxis.get(j));;
                String val1=(String) xaxis.get(j);
                float tc=Float.parseFloat(val1);
                //Check wheather the number is Integer or Float..........
                if((xplot.containsKey(tc))){
                    float new_value=  Float.parseFloat((String)xaxis.get(j));
                    String val2= (String) yaxis.get(j);
                    Object xcc =  xplot.get(new_value);
                    Object ycc =  yplot.get(val2);
                    StringBuffer label=new StringBuffer();
                    label.append("("+xaxis.get(j)+","+yaxis.get(j)+")");
                    canvas.drawText(String.valueOf(label), (int)xcc-10, (int)ycc-10, labels);
                    canvas.drawCircle((int) xcc, (int) ycc, 8, plot);
                }
                else {
                    float val;
                    //...............Float Logic.....
                    float xcc =Float.parseFloat((String) xaxis.get(j));
                    //...........Integer part separation........
                    int integer_part= (int) xcc;
                    //........Decimal part separation........
                    String dot=".";
                    int count=0;
                    for(int d=0;d<val1.length();d++){
                        if(String.valueOf(val1.charAt(d)).equals(dot)){
                            ++count;
                            break;
                        }
                    }
                    String decimal=val1.substring(count+1);
                    float decimal_part=Float.parseFloat((decimal));
                    //...........Distance between two Elements.................
                    int distance;
                    Object temp1=xplot.get((float)integer_part);
                    Object temp2;
                    int v=s-1;
                    if(j==v) {
                        temp2=xplot.get((float)integer_part-1);
                    }
                    else {
                        temp2=xplot.get((float)integer_part+1);
                    }
                    distance=(int)temp2-(int)temp1;
                    //.................Internal Distance Calculation..............
                    float internal_distance=(float)distance/100;
                    //..........New Pixel......
                    float pixel_new= (float)((float)internal_distance*decimal_part) ;
                    val=(int)temp1+pixel_new;
                    String val2= (String) yaxis.get(j);
                    Object ycc_f =  yplot.get(val2);
                    StringBuffer label=new StringBuffer();
                    label.append("("+xaxis.get(j)+","+yaxis.get(j)+")");
                    canvas.drawText(String.valueOf(label), (int)val-10, (int)ycc_f-10, labels);
                    canvas.drawCircle((int)val,(int) ycc_f, 8, plot);
                }
            }
        }
        if((xc==2)&&(yc==2)) {    //X And Y Number
            Object xcc_f,ycc_f;
            for (int j = 0; j < s; j++) {
                //....................Corresponding X Range....................//
                String val2_x=(String) xaxis.get(j);
                float tc_x=Float.parseFloat(val2_x);
                //Check wheather the number is Integer or Float..........
                if((xplot.containsKey(tc_x))) {
                    float new_value_x=  Float.parseFloat((String)xaxis.get(j));
                    String val1_x= (String) xaxis.get(j);
                    float temp_val1_x=Float.parseFloat(val1_x);
                    xcc_f = (int) xplot.get(temp_val1_x);
                }
                else {
                    float val_x;
                    //...............Float Logic.....
                    float xcc_x =Float.parseFloat((String) xaxis.get(j));
                    //...........Integer part separation........
                    int integer_part_x= (int) xcc_x;
                    //........Decimal part separation........
                    String dot_x=".";
                    int count_x=0;
                    for(int d=0;d<val2_x.length();d++) {
                        if(String.valueOf(val2_x.charAt(d)).equals(dot_x)) {
                            break;
                        }
                        ++count_x;
                    }
                    String decimal_x=val2_x.substring(count_x+1);
                    float decimal_part_x=Float.parseFloat((decimal_x));
                    //...........Distance between two Elements.................
                    int distance_x;
                    Object temp1=xplot.get((float)integer_part_x);
                    Object temp2;
                    int v=s-1;
                    if(j==v) {
                        temp2=xplot.get((float)integer_part_x-1);
                    }
                    else {
                        temp2=xplot.get((float)integer_part_x+1);
                    }
                    distance_x=(int)temp2-(int)temp1;
                    //.................Internal Distance Calculation..............
                    float internal_distance_x=(float)distance_x/100;
                    //..........New Pixel......
                    float pixel_new= (float)((float)internal_distance_x*decimal_part_x) ;
                    val_x=(int)temp1+pixel_new;
                    xcc_f = (int)val_x;
                }
                //....................Corresponding Y Range....................//
                String val2_y=(String) yaxis.get(j);
                float tc_y=Float.parseFloat(val2_y);
                //Check wheather the number is Integer or Float..........
                if((yplot.containsKey(tc_y))) {
                    float new_value_y=  Float.parseFloat((String)yaxis.get(j));
                    String val1_y= (String) yaxis.get(j);
                    float temp_val1_y=Float.parseFloat(val1_y);
                    ycc_f = (int) yplot.get(temp_val1_y);
                }
                else {
                    float val_y;
                    //...............Float Logic.....
                    float xcc_y =Float.parseFloat((String) yaxis.get(j));
                    //...........Integer part separation........
                    int integer_part= (int) xcc_y;
                    //........Decimal part separation........
                    String dot_y=".";
                    int count_y=0;
                    for(int d=0;d<val2_y.length();d++) {
                        if(String.valueOf(val2_y.charAt(d)).equals(dot_y)) {
                            ++count_y;
                            break;
                        }
                    }
                    String decimal_y=val2_y.substring(count_y+1);
                    float decimal_part_y=Float.parseFloat((decimal_y));
                    //...........Distance between two Elements.................
                    int distance_y;
                    Object temp1_y=yplot.get((float)integer_part);
                    Object temp2_y;
                    int v_y=s-1;
                    if(j==v_y) {
                        temp2_y=yplot.get((float)integer_part-1);
                    }
                    else {
                        temp2_y=yplot.get((float)integer_part+1);
                    }
                    distance_y=(int)temp2_y-(int)temp1_y;
                    //.................Internal Distance Calculation..............
                    float internal_distance_y=(float)distance_y/100;
                    //..........New Pixel......
                    float pixel_new= (float)((float)internal_distance_y*decimal_part_y) ;
                    val_y=(int)temp1_y+pixel_new;
                    ycc_f = (int)val_y;
                }
                StringBuffer label=new StringBuffer();
                label.append("("+xaxis.get(j)+","+yaxis.get(j)+")");
                canvas.drawText(String.valueOf(label), (int)xcc_f-10, (int)ycc_f-10, labels);

                canvas.drawCircle((int) xcc_f, (int)ycc_f, 8, plot);
            }
        }

    }
    public void start(int secs) {
       mTimerAnimator.setIntValues(secs);
        mTimerAnimator.setDuration(TimeUnit.SECONDS.toMillis(secs));
        mTimerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                no_of_iteration=(int)animation.getAnimatedValue();
                invalidate();
            }});
        mTimerAnimator.start();
    }

}
