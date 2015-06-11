package com.iuservice.lib.android.dagger.module;

import android.content.Context;

import com.iuservice.lib.android.dagger.DaggerApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-10.
 */
@Module
public class DaggerApplicationModule {

  private DaggerApplication m_application;

  public DaggerApplicationModule(DaggerApplication application) {
    m_application = application;
  }

  @Provides @Singleton
  DaggerApplication provideApplication() {
    return m_application;
  }

  @Provides @Singleton
  Context provideApplicationContext() {
    return m_application;
  }
}
