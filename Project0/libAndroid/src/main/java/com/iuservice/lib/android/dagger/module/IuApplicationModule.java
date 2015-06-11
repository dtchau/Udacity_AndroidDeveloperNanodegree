package com.iuservice.lib.android.dagger.module;

import android.content.Context;

import com.iuservice.lib.android.dagger.IuApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-10.
 */
@Module
public class IuApplicationModule {

  private IuApplication m_application;

  public IuApplicationModule(IuApplication application) {
    m_application = application;
  }

  @Provides
  @Singleton
  IuApplication provideApplication() {
    return m_application;
  }

  @Provides
  @Singleton
  Context provideApplicationContext() {
    return m_application;
  }
}
