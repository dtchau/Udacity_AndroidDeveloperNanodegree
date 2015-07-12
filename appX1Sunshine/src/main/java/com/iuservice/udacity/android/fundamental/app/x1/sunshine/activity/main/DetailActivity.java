package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main;

import android.os.Bundle;

import com.iuservice.udacity.android.fundamental.app.x1.sunshine.R;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.SunshineApplication;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.RootActivity;

public class DetailActivity extends RootActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((SunshineApplication) getApplication()).getComponent().inject(this);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_detail;
  }
}
