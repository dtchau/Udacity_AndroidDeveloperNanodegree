package com.iuservice.udacity.android.fundamental.app.x1.sunshine;

import android.content.Context;
import android.content.SharedPreferences;

import com.iuservice.lib.android.util.PreferenceUtil;

import javax.inject.Inject;

/**
 * @author Duong Tam Chau
 * @date 6/14/2015.
 */
public class SunshineApplicationSettings {

  private final Context m_context;
  private final SharedPreferences m_sharedPreference;

  @Inject
  public SunshineApplicationSettings(Context context, SharedPreferences sharedPreferences) {
    m_context = context;
    m_sharedPreference = sharedPreferences;
  }

  public String getUserLocation() {
    return PreferenceUtil.getString(m_context, m_sharedPreference, R.string.setting_location_key, R.string.setting_location_default);
  }

  public String getDisplayUnit() {
    return PreferenceUtil.getString(m_context, m_sharedPreference, R.string.setting_unit_key, R.string.setting_unit_metric);
  }

  public boolean isDisplayUnitInMetric() {
    return getDisplayUnit().equalsIgnoreCase(m_context.getString(R.string.setting_unit_metric));
  }
}
