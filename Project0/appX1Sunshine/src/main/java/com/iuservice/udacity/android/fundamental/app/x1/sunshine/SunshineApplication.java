package com.iuservice.udacity.android.fundamental.app.x1.sunshine;

import com.iuservice.lib.android.dagger.IuApplication;
import com.iuservice.lib.android.dagger.component.IuApplicationComponent;
import com.iuservice.lib.android.dagger.module.IuApplicationModule;
import com.iuservice.lib.android.dagger.module.IuApplicationModuleNetwork;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
public class SunshineApplication extends IuApplication {

  @Override
  protected IuApplicationComponent createApplicationComponent() {
    return DaggerSunshineApplicationComponent.builder()
        .iuApplicationModule(new IuApplicationModule(this))
        .iuApplicationModuleNetwork(new IuApplicationModuleNetwork())
        .build();
  }
}
