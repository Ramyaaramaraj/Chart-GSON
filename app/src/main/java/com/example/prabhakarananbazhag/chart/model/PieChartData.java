package com.example.prabhakarananbazhag.chart.model;

import java.io.Serializable;
import java.util.List;

public class PieChartData  implements Serializable{
    private List<Data> data;
    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
  public class Data {
        public String x;

      public String getX() {
          return x;
      }

      public void setX(String x) {
          this.x = x;
      }

      public Float getY() {
          return y;
      }

      public void setY(Float y) {
          this.y = y;
      }

      public String getColour() {
          return colour;
      }

      public void setColour(String colour) {
          this.colour = colour;
      }

      public Float y;
        public String  colour;
    }
}
