package com.iuservice.lib.android.dagger.module;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-10.
 */
@Module
public class IuApplicationModule {

  private Application m_application;

  public IuApplicationModule(Application application) {
    m_application = application;
  }

  @Provides
  @Singleton
  Application provideApplication() {
    return m_application;
  }

  @Provides
  @Singleton
  Context provideApplicationContext() {
    return m_application;
  }

  @Provides
  @Singleton
  LayoutInflater provideLayoutInflater() {
    return (LayoutInflater) m_application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
}
