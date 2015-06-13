package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iuservice.lib.android.dagger.qualifier.DateOnly;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.R;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.SunshineApplication;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.model.WeatherResult;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.service.WeatherService;

import java.text.DateFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherForecastFragment extends Fragment {

  @InjectView(R.id.list_view_forecast)
  ListView m_forecastListView;

  @Inject
  @DateOnly
  DateFormat m_dateFormat;
  @Inject
  WeatherService m_weatherService;

  private ArrayAdapter<String> m_weatherAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((SunshineApplication) getActivity().getApplication()).getComponent().inject(this);
    setHasOptionsMenu(true);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.forecast_fragment, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_refresh:
        fetchWeatherInfo();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View myFragment = inflater.inflate(R.layout.fragment_main, container, false);
    m_forecastListView = (ListView) myFragment.findViewById(R.id.list_view_forecast);

    m_weatherAdapter = new ArrayAdapter<String>(
        getActivity(),
        R.layout.fake_weather_list_item,
        R.id.weatherLabel,
        new ArrayList<String>()
    );

    m_forecastListView.setAdapter(m_weatherAdapter);

    return myFragment;
  }

  private void fetchWeatherInfo() {
    try {
      m_weatherService.getWeather("London", 7, new Callback<WeatherResult>() {
        @Override
        public void success(WeatherResult weatherResult, Response response) {
          for (WeatherResult.WeatherData weatherData : weatherResult.getList()) {
            WeatherResult.WeatherData.Temp temp = weatherData.getTemp();
            m_weatherAdapter.add(
                String.format("%s - %s. From %3.0f to %3.0f"
                    , m_dateFormat.format(weatherData.getDt())
                    , weatherData.getWeather().get(0).getDescription()
                    , temp.getMin()
                    , temp.getMax())
            );
          }
        }

        @Override
        public void failure(RetrofitError error) {
          Log.e(this.getClass().getCanonicalName(), error.getMessage(), error);
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
