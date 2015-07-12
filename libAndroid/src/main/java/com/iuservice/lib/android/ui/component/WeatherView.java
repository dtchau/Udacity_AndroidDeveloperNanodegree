package com.iuservice.lib.android.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.iuservice.lib.android.R;

/**
 * Created by dtc on 6/7/2015.
 */
public class WeatherView extends FrameLayout {

  public WeatherView(Context context, AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.component_weather_view, this, true);
  }
}
