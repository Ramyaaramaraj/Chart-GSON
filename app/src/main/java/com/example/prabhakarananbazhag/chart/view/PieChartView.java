package com.example.prabhakarananbazhag.chart.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.prabhakarananbazhag.chart.model.PieChartData;

import java.util.concurrent.TimeUnit;

public class PieChartView extends View{
    Paint paint,paint1;
    PieChartData pieChartData;
    private ValueAnimator mTimerAnimator;
    int no_of_iteration=0;

    public PieChartView(Context context){
        super(context);
    }
    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint1=new Paint();
        paint.setColor(Color.BLACK);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float start=0f;
        if(pieChartData!=null){
            float[] sweep=scale();
            int z=canvas.getWidth()/20;
            int v=canvas.getWidth()/5;
            int w=canvas.getWidth()/30;
            int x=canvas.getWidth()-z;
            int y= canvas.getHeight()-z;
            RectF rectF = new RectF(z,z,x,y);
            RectF rectF1=new RectF(w,w,x+10,y+10);
            RectF rectF2=new RectF(v,v,getWidth()-v,getHeight()-v); Paint paint2=new Paint();
            for(int i=0;i<pieChartData.getData().size();++i){
                paint.setColor(Color.parseColor(String.valueOf(pieChartData.getData().get(i).getColour())));
                Path p2=new Path();
                canvas.drawArc(rectF, start, sweep[i], true, paint);
                p2.addArc(rectF,start,sweep[i]);
                Path p = new Path();
                p.addArc(rectF1, start, sweep[i]);
                Path p3=new Path();
                p3.addArc(rectF2,start,sweep[i]);
                PathMeasure pathMeasure = new PathMeasure(p2, false);
                float pathLength = pathMeasure.getLength();
                paint.setColor(Color.BLACK);
                paint.setTextSize(getWidth() / 25);
                paint.setTextAlign(Paint.Align.CENTER);
                String name = String.valueOf(pieChartData.getData().get(i).getX());
                if (paint.measureText(name) > pathLength) {
                    while (paint.measureText(name) > pathLength) {
                        name = name.substring(0, name.length() - 1);
                    }
                    canvas.drawTextOnPath(name.substring(0, name.length() - 1).concat("..."), p, 0, 0, paint);
                } else {
                    canvas.drawTextOnPath(String.valueOf(pieChartData.getData().get(i).getX()), p, 0, 0, paint);
                }
                paint2.setColor(Color.RED);
                paint2.setTextSize(getWidth()/20);
                paint2.setTextAlign(Paint.Align.CENTER);
                canvas.drawTextOnPath(String.valueOf((pieChartData.getData().get(i).getY())),p3,0,0,paint2);
                start += sweep[i];
            }

        }else{
            return;
        }
    }
    private float[] scale() {
        float[] sweep=new float[pieChartData.getData().size()];
        float total = getTotal();
        for(int i=0;i<pieChartData.getData().size();i++){
            sweep[i] = (Float.parseFloat(String.valueOf(pieChartData.getData().get(i).getY())) /total) * 360;
        }
        return sweep;
    }
    private float getTotal(){
        float total = 0;
        for(int i=0;i<pieChartData.getData().size();i++) {
            float val= Float.parseFloat(String.valueOf(pieChartData.getData().get(i).getY()));
            total += val;
        }
        return total;
    }
   /* public void start(int secs) {
        mTimerAnimator.setIntValues(0,360);
        mTimerAnimator.setDuration(TimeUnit.SECONDS.toMillis(secs));
        mTimerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                no_of_iteration=(int)animation.getAnimatedValue();
                invalidate();
            }});
        mTimerAnimator.start();
    }*/
    public void setdata(PieChartData chartdata){
        pieChartData=chartdata;
        postInvalidate();
    }
}
