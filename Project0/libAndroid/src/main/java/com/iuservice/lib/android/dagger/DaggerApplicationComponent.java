package com.iuservice.lib.android.dagger;

import com.iuservice.lib.android.dagger.module.DaggerApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-10.
 */
@Singleton
@Component(modules = {DaggerApplicationModule.class})
public interface DaggerApplicationComponent {

  void inject(DaggerActivity daggerActivity);
}
