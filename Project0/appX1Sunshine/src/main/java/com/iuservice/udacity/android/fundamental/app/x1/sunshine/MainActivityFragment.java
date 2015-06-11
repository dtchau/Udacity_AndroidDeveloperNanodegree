package com.iuservice.udacity.android.fundamental.app.x1.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iuservice.udacity.android.fundamental.app.x1.sunshine.service.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.InjectView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

  @InjectView(R.id.list_view_forecast)
  ListView m_forecastListView;

  @Inject
  WeatherService m_weatherService;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View myFragment = inflater.inflate(R.layout.fragment_main, container, false);
    m_forecastListView = (ListView) myFragment.findViewById(R.id.list_view_forecast);

    ArrayList<String> fakeData = new ArrayList<>(51);
    try {
      String weather = m_weatherService.getWeather();
      fakeData.add(weather);
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (int i = 1; i < 50; ++i) {
      fakeData.add(String.format("Item #%3d", i));
    }

    ArrayAdapter<String> weatherAdapter = new ArrayAdapter<String>(
        getActivity(),
        R.layout.fake_weather_list_item,
        R.id.weatherLabel,
        fakeData
    );

    m_forecastListView.setAdapter(weatherAdapter);

    return myFragment;
  }
}
