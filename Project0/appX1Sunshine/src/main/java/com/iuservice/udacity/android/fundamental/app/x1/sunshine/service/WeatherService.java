package com.iuservice.udacity.android.fundamental.app.x1.sunshine.service;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javax.inject.Inject;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
public class WeatherService {

  private OkHttpClient m_okHttpClient;
  private Request.Builder m_requestBuilder;

  @Inject
  public WeatherService(OkHttpClient okHttpClient, Request.Builder requestBuilder) {
    m_okHttpClient = okHttpClient;
    m_requestBuilder = requestBuilder;
  }

  public String getWeather() throws IOException {
    String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk";
    Request request  = m_requestBuilder.url(url).build();
    Response response = m_okHttpClient.newCall(request).execute();
    Log.e("TESTING", response.body().string());
//    return response.body().string();
    return "Hello there";
  }
}
