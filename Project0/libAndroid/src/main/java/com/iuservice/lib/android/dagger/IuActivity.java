package com.iuservice.lib.android.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @d2015-06-10.
 */
public class IuActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((IuApplication) getApplication()).inject(this);
  }
}
