package com.iuservice.udacity.android.fundamental.app.x1.sunshine.dagger.component;

import com.iuservice.lib.android.dagger.module.IuApplicationModule;
import com.iuservice.lib.android.dagger.module.IuApplicationModuleDateFormat;
import com.iuservice.lib.android.dagger.module.IuApplicationModuleRetrofit;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.SunshineApplication;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main.DetailActivity;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main.DetailActivityFragment;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main.WeatherForecastActivity;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main.WeatherForecastFragment;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.setting.SettingActivity;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.dagger.module.SunshineModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
@Singleton
@Component(modules = {
    IuApplicationModule.class,
    IuApplicationModuleRetrofit.class,
    IuApplicationModuleDateFormat.class,
    SunshineModule.class
})
public interface SunshineApplicationComponent {

  void inject(SunshineApplication application);

  void inject(WeatherForecastActivity activity);

  void inject(WeatherForecastFragment fragment);

  void inject(DetailActivity activity);

  void inject(DetailActivityFragment fragment);

  void inject(SettingActivity activity);
}
