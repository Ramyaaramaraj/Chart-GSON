package com.example.prabhakarananbazhag.chart.model;
import java.util.ArrayList;
public class AxisChartData {

    public ArrayList<Plot>plot;

    public ArrayList<Plot> getPlot() {
        return plot;
    }

    public ArrayList<Label> getLabel() {
        return label;
    }

    public ArrayList<Label>label;

    public class Plot {
        public String x;

        public String getX() {
            return x;
        }

        public String getY() {
            return y;
        }

        public String y;
        public String color;

        public String getColor() {
            return color;
        }
    }

    public class Label {
        public String x;
        public String y;

        public String getX() {
            return x;
        }

        public String getY() {
            return y;
        }
    }

}

