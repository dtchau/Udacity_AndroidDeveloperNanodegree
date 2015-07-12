package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.iuservice.lib.android.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;


public class MainActivity extends BaseActivity {

  @InjectView(R.id.imageView)
  ImageView m_imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Picasso.with(this).load("http://ww1.prweb.com/prfiles/2014/04/10/11752526/gI_134971_best-image-web-hosting.png").into(m_imageView);
  }

  @Override
  protected int getActivityLayoutId() {
    return R.layout.activity_base;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_base, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
