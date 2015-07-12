package com.iuservice.lib.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import butterknife.ButterKnife;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-12.
 */
public abstract class BaseActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getActivityLayoutId());
    ButterKnife.inject(this);
  }

  protected abstract int getActivityLayoutId();
}
