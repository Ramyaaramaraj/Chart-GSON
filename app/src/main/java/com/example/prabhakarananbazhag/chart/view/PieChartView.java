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
        float currentStartArcPosition=0f;
        float currentSweep;
        float totalValues=0;
        public PieChartView(Context context){
            super(context);
        }
        public PieChartView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            paint=new Paint();
            paint1=new Paint();
            paint1.setColor(Color.BLACK);
            mTimerAnimator=new ValueAnimator();
        }
        public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(pieChartData!=null){
                int z=canvas.getWidth()/20;
                int w=canvas.getWidth()/25;
                int v=canvas.getWidth()/5;
                int x=canvas.getWidth()-z;
                int y= canvas.getHeight()-z;
                currentStartArcPosition=0f;
                int sweepCount=0;
                RectF rectF = new RectF(z,z,x,y);
                RectF  rectF1=new RectF(w,w,(x+15),(y+15));
                RectF  rectF2=new RectF(v,v,getWidth()-v,getHeight()-v);
                for(int i=0;i<pieChartData.getData().size();i++){
                    paint.setColor(Color.parseColor(String.valueOf(pieChartData.getData().get(i).getColour())));
                    currentSweep = (pieChartData.getData().get(i).getY() / totalValues) * 360;
                    float start = currentStartArcPosition;
                    for (float sweep = 1; sweep <= currentSweep; sweep++) {
                        if(sweepCount>=no_of_iteration){
                            break;
                        }else{
                            canvas.drawArc(rectF, start, 2, true, paint);
                            start++;
                        }
                        sweepCount++;
                    }
                    if(currentStartArcPosition+currentSweep<=no_of_iteration){
                        Path p=new Path();
                        Path  p3=new Path();
                        p.addArc(rectF1, currentStartArcPosition,currentSweep);
                        p3.addArc(rectF2,currentStartArcPosition,currentSweep);
                        PathMeasure  pathMeasure = new PathMeasure(p, false);
                        float pathLength = pathMeasure.getLength();
                        paint.setColor(Color.BLACK);
                        paint.setTextSize(getWidth() / 25);
                        paint.setTextAlign(Paint.Align.CENTER);
                        String name = String.valueOf(pieChartData.getData().get(i).getX());
                        if (paint.measureText(name) > pathLength) {
                            while (paint.measureText(name) > pathLength) {
                                name = name.substring(0, name.length() - 1);}
                            canvas.drawTextOnPath(name.substring(0, name.length() - 1).concat("..."), p, 0, 0, paint);
                        } else {
                            canvas.drawTextOnPath(String.valueOf(pieChartData.getData().get(i).getX()), p, 0, 0, paint);
                        }
                        Paint paint2=new Paint();
                        paint2.setColor(Color.WHITE);
                        paint2.setTextSize(getWidth()/20);
                        paint2.setTextAlign(Paint.Align.CENTER);
                        canvas.drawTextOnPath(String.valueOf((pieChartData.getData().get(i).getY())),p3,0,0,paint2);
                    }
                    currentStartArcPosition += currentSweep;
                }
            }else{
                return;
            }
        }
        public void start(int secs) {
            mTimerAnimator.setIntValues(0,360);
            mTimerAnimator.setDuration(TimeUnit.SECONDS.toMillis(secs));
            mTimerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    no_of_iteration=(int)animation.getAnimatedValue();
                    invalidate();
                }});
            mTimerAnimator.start();
        }

        public void setdata(PieChartData chartdata){
            pieChartData=chartdata;
            totalValues=0;
            for(int i=0;i<pieChartData.getData().size();i++){
                totalValues+=pieChartData.getData().get(i).getY();
            }
        }
    }

