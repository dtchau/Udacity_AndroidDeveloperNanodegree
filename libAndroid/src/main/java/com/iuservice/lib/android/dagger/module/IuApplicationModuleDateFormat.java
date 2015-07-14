package com.iuservice.lib.android.dagger.module;

import com.iuservice.lib.android.dagger.qualifier.DateOnly;

import java.text.DateFormat;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Duong Tam Chau
 * @date 6/13/2015.
 */
@Module
public class IuApplicationModuleDateFormat {

  @Provides
  @Singleton
  @DateOnly
  DateFormat provideDateOnlyFormat() {
    return DateFormat.getDateInstance();
  }

  @Provides
  @Singleton
  DateFormat provideDateTimeFormat() {
    return DateFormat.getDateTimeInstance();
  }
}
