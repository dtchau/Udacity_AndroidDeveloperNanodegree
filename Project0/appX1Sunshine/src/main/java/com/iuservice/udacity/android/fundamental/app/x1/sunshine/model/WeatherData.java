package com.iuservice.udacity.android.fundamental.app.x1.sunshine.model;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-12.
 */
public class WeatherData {

  private City city;

  public City getCity() {
    return city;
  }

  public static class City {
    private Long id;
    private String name;
    private Coord coord;
    private String country;
    private Long population;

    public String getName() {
      return name;
    }

    public static class Coord {
      private Double lon;
      private Double lat;
    }
  }
}
