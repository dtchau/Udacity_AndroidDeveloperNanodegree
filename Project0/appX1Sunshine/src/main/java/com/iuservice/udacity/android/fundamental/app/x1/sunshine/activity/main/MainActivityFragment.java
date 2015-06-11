package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.JsonElement;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.R;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.SunshineApplication;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.service.WeatherService;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

  @InjectView(R.id.list_view_forecast)
  ListView m_forecastListView;

  @Inject
  WeatherService m_weatherService;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((SunshineApplication) getActivity().getApplication()).getComponent().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View myFragment = inflater.inflate(R.layout.fragment_main, container, false);
    m_forecastListView = (ListView) myFragment.findViewById(R.id.list_view_forecast);

    final ArrayList<String> fakeData = new ArrayList<>(51);
    try {
      m_weatherService.getWeather(new Callback<JsonElement>() {
        @Override
        public void success(JsonElement element, Response response) {
          fakeData.add(element.toString());
          Log.e("TESTING", element.toString());

        }

        @Override
        public void failure(RetrofitError error) {
          Log.e("TESTING", "FAILED");
          Log.e("TESTING", error.getMessage());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (int i = 1; i < 5; ++i) {
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
