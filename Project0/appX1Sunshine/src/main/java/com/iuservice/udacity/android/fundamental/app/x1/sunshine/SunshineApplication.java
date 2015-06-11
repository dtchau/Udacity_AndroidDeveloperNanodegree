package com.iuservice.udacity.android.fundamental.app.x1.sunshine;

import android.app.Application;

import com.iuservice.lib.android.dagger.module.IuApplicationModule;
import com.iuservice.lib.android.dagger.module.IuApplicationModuleNetwork;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.dagger.component.DaggerSunshineApplicationComponent;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.dagger.component.SunshineApplicationComponent;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.dagger.module.SunshineModule;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
public class SunshineApplication extends Application {

  private SunshineApplicationComponent m_component;

  @Override
  public void onCreate() {
    super.onCreate();
    m_component = createApplicationComponent();
    m_component.inject(this);
  }

  public SunshineApplicationComponent getComponent() {
    return m_component;
  }

  private SunshineApplicationComponent createApplicationComponent() {
    return DaggerSunshineApplicationComponent.builder()
        .iuApplicationModule(new IuApplicationModule(this))
        .iuApplicationModuleNetwork(new IuApplicationModuleNetwork())
        .sunshineModule(new SunshineModule())
        .build();
  }
}
