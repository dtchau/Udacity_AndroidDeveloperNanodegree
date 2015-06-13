package com.iuservice.lib.android.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iuservice.lib.android.gson.DateFromUnixSecond;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
@Module
public class IuApplicationModuleNetwork {

  @Provides
  @Singleton
  Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(DateFromUnixSecond.class, new DateFromUnixSecond.DateFromUnixSecondTypeAdapter());
    return gsonBuilder.create();
  }

  @Provides
  @Singleton
  GsonConverter provideGsonConverter(Gson gson) {
    return new GsonConverter(gson);
  }

  @Provides
  @Singleton
  RestAdapter.Builder provideRestAdapterBuilder(GsonConverter gsonConverter) {
    RestAdapter.Builder builder = new RestAdapter.Builder();
    builder.setConverter(gsonConverter);
    return builder;
  }
}
