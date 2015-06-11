package com.iuservice.lib.android.dagger;

import android.app.Application;

import com.iuservice.lib.android.dagger.component.IuApplicationComponent;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-10.
 */
public abstract class IuApplication extends Application {

  private IuApplicationComponent m_applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    m_applicationComponent = createApplicationComponent();
    m_applicationComponent.inject(this);
  }

  public void inject(IuActivity activity) {
    m_applicationComponent.inject(activity);
  }

  public void inject(IuFragment fragment) {
    m_applicationComponent.inject(fragment);
  }

  protected abstract IuApplicationComponent createApplicationComponent();
}
