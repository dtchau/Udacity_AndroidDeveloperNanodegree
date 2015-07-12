package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.iuservice.udacity.android.fundamental.app.x1.sunshine.R;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.SunshineApplication;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author Duong Tam Chau
 * @date 6/14/2015.
 */
public class SettingActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

  @Inject
  SharedPreferences m_sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((SunshineApplication) getApplication()).getComponent().inject(this);
    addPreferencesFromResource(R.xml.settings);

    for (Map.Entry<String, ?> preferenceEntry : m_sharedPreferences.getAll().entrySet()) {
      Log.e("TESTING", preferenceEntry.getKey() + " - " + preferenceEntry.getValue());
      bindPreferencesSummaryToValue(findPreference(preferenceEntry.getKey()));
    }
  }

  private void bindPreferencesSummaryToValue(Preference preference) {
    preference.setOnPreferenceChangeListener(this);
    onPreferenceChange(preference, m_sharedPreferences.getString(preference.getKey(), ""));
  }

  @Override
  public boolean onPreferenceChange(Preference preference, Object newValue) {
    if (preference instanceof ListPreference) {
      ListPreference listPreference = (ListPreference) preference;
      int prefIndex = listPreference.findIndexOfValue(newValue.toString());
      if (prefIndex >= 0) {
        preference.setSummary(listPreference.getEntries()[prefIndex]);
      }
    } else {
      preference.setSummary(newValue.toString());
    }

    return true;
  }
}
