package com.iuservice.udacity.android.fundamental.app.x1.sunshine.model;

import com.iuservice.lib.android.gson.DateFromUnixSecond;

import java.util.List;

import lombok.Data;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-12.
 */
@Data
public class WeatherResult {

  private City city;
  private List<WeatherData> list;

  @Data
  public static class City {
    private Long id;
    private String name;
    private String country;
    private Coord coord;
    private Long population;

    @Data
    public static class Coord {
      private Double lon;
      private Double lat;
    }
  }

  @Data
  public static class WeatherData {
    private DateFromUnixSecond dt;
    private Temp temp;
    private List<Weather> weather;

    @Data
    public static class Temp {
      private Double day;
      private Double min;
      private Double max;
      private Double night;
      private Double eve;
      private Double morn;
    }

    @Data
    public static class Weather {
      private Integer id;
      private String main;
      private String description;
      private String icon;
    }
  }
}
