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
      public int y;
      public String  colour;
      public String getX() {
          return x;
      }

      public void setX(String x) {
          this.x = x;
      }

      public int getY() {
          return y;
      }

      public void setY(int y) {
          this.y = y;
      }

      public String getColour() {
          return colour;
      }

      public void setColour(String colour) {
          this.colour = colour;
      }

    }
}
