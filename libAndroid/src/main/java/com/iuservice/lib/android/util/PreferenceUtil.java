package com.iuservice.lib.android.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Duong Tam Chau
 * @date 6/14/2015.
 */
public class PreferenceUtil {

  public static String getString(Context context, SharedPreferences sharedPreferences, int preference_key_id, int preference_default_id) {
    return sharedPreferences.getString(context.getString(preference_key_id), context.getString(preference_default_id));
  }
}
