package com.iuservice.udacity.android.fundamental.app.x1.sunshine.service;

import com.iuservice.udacity.android.fundamental.app.x1.sunshine.model.WeatherResult;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
public interface WeatherService {

  @GET("/data/2.5/forecast/daily?q=London&mode=json&units=metric&cnt=7")
  void getWeather(Callback<WeatherResult> callback);
}
