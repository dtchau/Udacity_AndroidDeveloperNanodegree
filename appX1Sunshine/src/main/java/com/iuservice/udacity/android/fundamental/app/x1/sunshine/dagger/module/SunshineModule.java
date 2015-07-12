package com.iuservice.udacity.android.fundamental.app.x1.sunshine.dagger.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.iuservice.udacity.android.fundamental.app.x1.sunshine.service.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
@Module
public class SunshineModule {

  private Application m_application;

  public SunshineModule(Application application) {
    m_application = application;
  }

  @Provides
  @Singleton
  SharedPreferences provideDefaultSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(m_application);
  }

  @Provides
  @Singleton
  RestAdapter provideRestAdapter(RestAdapter.Builder restAdapterBuilder) {
    return restAdapterBuilder.setEndpoint("http://api.openweathermap.org/").build();
  }

  @Provides
  @Singleton
  WeatherService provideWeatherService(RestAdapter restAdapter) {
    return restAdapter.create(WeatherService.class);
  }
}
