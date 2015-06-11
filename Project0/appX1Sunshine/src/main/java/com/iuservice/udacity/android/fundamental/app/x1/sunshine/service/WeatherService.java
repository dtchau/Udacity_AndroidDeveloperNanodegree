package com.iuservice.udacity.android.fundamental.app.x1.sunshine.service;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
public interface WeatherService {

  @GET("/data/2.5/weather?q=London,uk")
  void getWeather(Callback<JsonElement> callback);
}
