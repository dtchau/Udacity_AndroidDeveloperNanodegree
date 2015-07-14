package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer;

import android.view.Menu;
import android.view.MenuItem;

import com.iuservice.lib.android.ui.activity.BaseActivity;

public abstract class MainActivity extends BaseActivity {

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
