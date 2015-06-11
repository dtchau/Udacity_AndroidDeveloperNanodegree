package com.iuservice.lib.android.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
@Module
public class IuApplicationModuleNetwork {

  @Provides
  @Singleton
  RestAdapter.Builder provideRestAdapterBuilder() {
    return new RestAdapter.Builder();
  }
}
