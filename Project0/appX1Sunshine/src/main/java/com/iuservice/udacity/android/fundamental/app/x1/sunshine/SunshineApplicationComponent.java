package com.iuservice.udacity.android.fundamental.app.x1.sunshine;

import com.iuservice.lib.android.dagger.component.IuApplicationComponent;
import com.iuservice.lib.android.dagger.module.IuApplicationModule;
import com.iuservice.lib.android.dagger.module.IuApplicationModuleNetwork;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
@Singleton
@Component(modules = {IuApplicationModule.class, IuApplicationModuleNetwork.class})
public interface SunshineApplicationComponent extends IuApplicationComponent {
}
