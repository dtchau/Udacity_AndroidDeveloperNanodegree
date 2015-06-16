package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.iuservice.lib.android.util.ActivityUtil;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.R;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.SunshineApplicationSettings;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.setting.SettingActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author Duong Tam Chau
 * @date 6/13/2015.
 */
public abstract class RootActivity extends ActionBarActivity {

  @Inject
  SunshineApplicationSettings m_applicationSettings;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    ButterKnife.inject(this);
    Log.d("LifeCycle", String.format("%s onCreate()", getLocalClassName()));
  }


  @Override
  protected void onStart() {
    super.onStart();
    Log.d("LifeCycle", String.format("%s onStart()", getLocalClassName()));
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d("LifeCycle", String.format("%s onPause()", getLocalClassName()));
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d("LifeCycle", String.format("%s onResume()", getLocalClassName()));
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d("LifeCycle", String.format("%s onStop()", getLocalClassName()));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d("LifeCycle", String.format("%s onDestroy()", getLocalClassName()));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        ActivityUtil.startActivity(this, SettingActivity.class);
        return true;
      case R.id.action_show_location:
        showUserLocation();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void showUserLocation() {
    try {
      ActivityUtil.startActionView(this, Uri.parse(String.format("geo:?q=%s", m_applicationSettings.getUserLocation())));
    } catch (ActivityUtil.NoHandlerAvailableException e) {
      Toast.makeText(this, "Can't find a handler for you action.", Toast.LENGTH_LONG);
    }
  }

  protected abstract int getLayoutId();
}
