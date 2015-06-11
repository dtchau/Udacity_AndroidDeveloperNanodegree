package com.iuservice.lib.android.dagger.module;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
@Module
public class IuApplicationModuleNetwork {

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient() {
    return new OkHttpClient();
  }

  @Provides
  @Singleton
  Request.Builder provideRequestBuilder() {
    return new Request.Builder();
  }
}
