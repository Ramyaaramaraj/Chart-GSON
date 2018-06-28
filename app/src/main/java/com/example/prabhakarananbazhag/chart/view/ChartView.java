package com.example.prabhakarananbazhag.chart.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class ChartView extends View{
    Paint plot = new Paint(Color.BLACK);
    Paint paint=new Paint(Color.GRAY);
    Paint axis=new Paint(Color.RED);
    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLACK);

        plot.setColor(Color.BLACK);
        axis.setColor(Color.RED);
    }
    public ChartView(Context context) {
        super(context);
    }
    public HashMap yNumber(ArrayList Yaxis, Canvas canvas, int length, int breadth, int dec) {
        //................Yarray Creation.........
        int yaxis[]=new int[Yaxis.size()];
        for(int i=0;i<Yaxis.size();i++) {
            float temp=Float.parseFloat((String) Yaxis.get(i));
            yaxis[i]= (int)(temp) ;
        }
        //.................TempArray Generation..........
        //.....................Y.................
        int temp_yaxis[] =new  int[Yaxis.size()];
        System.arraycopy(yaxis, 0,temp_yaxis, 0, Yaxis.size());
        Arrays.sort(temp_yaxis);
        int ymin = temp_yaxis[0];
        int ymax = temp_yaxis[Yaxis.size() - 1];
        //...............yscale Generation..................
        int yinc=(ymax-ymin)/Yaxis.size();
        ArrayList yscale=new ArrayList();
        int temp=ymin;
        if(yinc!=0)
        {
            for(;;) {
                if(temp<=ymax) {
                    yscale.add(temp);
                    temp=temp+yinc;
                }
                else {
                    yscale.add(temp);
                    break;
                }
            }
        }
        else
        {
            yinc=1;
            for(;;) {
                if(temp<=ymax) {
                    yscale.add(temp);
                    temp=temp+yinc;
                }
                else {
                    yscale.add(temp);
                    break;
                }
            }
        }
        int yscale_count=yscale.size();
        //...........Horizontal Lines.................
        int hxs = dec, hxst =breadth-dec, hys =length-dec, hyst =length-dec;
        int ysplit=((length-dec)-dec)/yscale_count;
        for (int i = 0; i <yscale_count; i++) {
            canvas.drawLine(hxs, hys, hxst, hyst,plot);
            hys = hys -ysplit;
            hyst = hyst - ysplit;
        }
        //.............ypoint fixing and Yscale Printing................
        int xstart=dec;  int ystart=length-dec-ysplit;
        HashMap ypixel = new HashMap();
        for(int i=0;i<yscale_count;i++) {
            int count;
            canvas.drawCircle(xstart, ystart, 5, paint);
            //...............Resizing the txt...............//
            axis.setTextSize(130);
            while(axis.measureText(String.valueOf(yscale.get(i)))>dec){
                axis.setTextSize(axis.getTextSize()-1);
            }
            int Resize= (int) (axis.getTextSize()/2);
            axis.setTextSize(Resize);
            canvas.drawText(String.valueOf((int) yscale.get(i)), xstart-2*(axis.getTextSize()), ystart+5,axis);
            //..................................................//
            ypixel.put(Float.parseFloat(String.valueOf(yscale.get(i))), ystart);
            int plot=(Integer) yscale.get(i);
            int temp_inc=ysplit/(yinc);
            if(temp_inc!=0) {
                for (count = 1; count <= yinc; count++) {
                    ++plot;
                    ypixel.put((float) plot, ystart - (temp_inc * count));
                }
                ystart-=ysplit;
            }
            else
            {
                temp_inc=1;
                for (count = 1; count <= yinc; count++) {
                    ++plot;
                    ypixel.put((float) plot, ystart - (temp_inc * count));
                }
                ystart-=ysplit;
            }
        }
        return ypixel;
    }
    public HashMap yString(ArrayList Yaxis, Canvas canvas, int length, int breadth,int dec) {
        //...........Horizontal Lines.................
        int hxs = dec, hxst =breadth-dec, hys =length-dec, hyst =length-dec;
        int ysplit=((length-dec)-dec)/Yaxis.size();
        for (int i = 0; i <Yaxis.size(); i++) {
            canvas.drawLine(hxs, hys, hxst, hyst,plot);
            hys = hys -ysplit;
            hyst = hyst - ysplit;
        }
        //.............Ypoint fixing and Yscale Printing...............
        int xstart=dec;  int ystart=length-dec-ysplit;
        HashMap ypixel = new HashMap();
        for(int i=0;i<Yaxis.size();i++) {
            canvas.drawCircle(xstart, ystart, 5, paint);
            canvas.drawText(String.valueOf((Object) Yaxis.get(i)), xstart-(dec-10), ystart+5, axis);
            ypixel.put(Yaxis.get(i), ystart);
            ystart=ystart-ysplit;
        }
        return ypixel;
    }
    public HashMap xString(ArrayList Xaxis,Canvas canvas,int length,int breadth,int dec) {
        //...........Vertical Lines...............
        int vxs = dec, vxst =dec, vys = dec, vyst = length-dec;
        int xsplit=((breadth-dec)-dec)/Xaxis.size();
        for (int i = 0; i <Xaxis.size(); i++) {
            canvas.drawLine(vxs, vys, vxst, vyst, plot);
            vxs = vxs + xsplit;
            vxst = vxst + xsplit;
        }
        //.............Xpoint fixing and Xscale Printing...............
        int xstart=dec+xsplit;int ystart=length-dec;
        HashMap xpixel = new HashMap();
        for(int i=0;i<Xaxis.size();i++) {
            canvas.drawCircle(xstart, ystart, 5, paint);
            canvas.drawText( String.valueOf( Xaxis.get(i)), xstart, ystart+(dec/3),axis);
            xpixel.put( Xaxis.get(i), xstart);
            xstart+=xsplit;
        }
        return  xpixel;
    }
    public HashMap xNumber(ArrayList Xaxis,Canvas canvas,int length,int breadth,int dec) {
        //.............Xarray Creation................
        int xaxisvalues[]=new int[Xaxis.size()];
        for(int i=0;i<Xaxis.size();i++) {
            float temp=Float.parseFloat((String) Xaxis.get(i));
            xaxisvalues[i]= (int)(temp) ;
        }
        //.................TempArray Generation..........
        //..............X..............
        int temp_xaxis[] =new  int[Xaxis.size()];
        System.arraycopy(xaxisvalues, 0,temp_xaxis, 0, Xaxis.size());
        Arrays.sort(temp_xaxis);
        int xmin = temp_xaxis[0];
        int xmax = temp_xaxis[Xaxis.size() - 1];
        //................Xscale Generation..................
        int xinc= (int) ((xmax-xmin)/Xaxis.size());
        ArrayList xscale=new ArrayList();
        int temp=(int)xmin;
        if(xinc!=0)
        {
            for(;;) {
                if(temp<=xmax) {
                    xscale.add(temp);
                    temp=temp+xinc;
                }
                else {
                    xscale.add(temp);
                    break;
                }
            }
        }
        else
        {
            xinc=1;
            for(;;) {
                if(temp<=xmax) {
                    xscale.add(temp);
                    temp=temp+xinc;
                }
                else {
                    xscale.add(temp);
                    break;
                }
            }
        }
        int xscale_count=xscale.size();
        //...........Vertical Lines...............
        int vxs = dec, vxst =dec, vys = dec, vyst = length-dec;
        int xsplit=((breadth-dec)-dec)/xscale_count;
        for (int i = 0; i <xscale_count; i++) {
            canvas.drawLine(vxs, vys, vxst, vyst, plot);
            vxs = vxs + xsplit;
            vxst = vxst + xsplit;
        }
        //...........xpoint fixing and Xscale Printing...................
        int xstart=dec+xsplit;int ystart=length-dec;
        HashMap xpixel = new HashMap();
        int temp_inc=(int) (xsplit)/(xinc);
        for(int i=0;i<xscale_count;i++) {
            int count;
            canvas.drawCircle(xstart, ystart, 5, paint);

            //...............Resizing the txt...............//
            axis.setTextSize(130);
            while(axis.measureText(String.valueOf(xscale.get(i)))>xsplit){
                axis.setTextSize(axis.getTextSize()-1);
            }
            int Resize= (int) (axis.getTextSize()/2);
            axis.setTextSize(Resize);
            canvas.drawText(String.valueOf((int) xscale.get(i)), xstart-(axis.getTextSize()), ystart+(dec/3),axis);
            //.....................................................//
            xpixel.put(Float.parseFloat(String.valueOf(xscale.get(i))), xstart);
            int plot=( int) xscale.get(i);
            if(temp_inc!=0) {
                for (count = 1; count <= xinc; count++) {
                    ++plot;
                    xpixel.put((float) plot, xstart + (temp_inc * count));
                }
                xstart += xsplit;
            }
            else
            {
                temp_inc=0;
                for (count = 1; count <= xinc; count++) {
                    ++plot;
                    xpixel.put((float) plot, xstart + (temp_inc * count));
                }
                xstart += xsplit;

            }
        }
        return  xpixel;
    }
    //........................Function To check X Range Format.......................
    public int xFormat(String xval) {
        int count=0;
        for(int i = 0; i < xval.length(); i++) {
            if((Character.isDigit(xval.charAt(i)))||(Boolean)((String.valueOf(xval.charAt(i))).equals("."))) {
                ++count;
            }
            else {
                count=0;
                break;
            }
        }
        return count;
    }
    //........................Function To check y Range Format.......................
    public int yFormat(String yval) {
        int count=0;
        for(int i = 0; i < yval.length(); i++) {
            if((Character.isDigit(yval.charAt(i)))||(Boolean)((String.valueOf(yval.charAt(i))).equals("."))) {
                ++count;
            }
            else {
                count=0;
                break;
            }
        }
        return count;
    }
}
