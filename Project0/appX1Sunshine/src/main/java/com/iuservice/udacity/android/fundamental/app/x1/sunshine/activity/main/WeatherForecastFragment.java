package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iuservice.lib.android.dagger.qualifier.DateOnly;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.R;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.SunshineApplication;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.RootFragment;
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
public class WeatherForecastFragment extends RootFragment {

  @InjectView(R.id.list_view_forecast)
  ListView m_forecastListView;

  Context m_context;
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
    m_context = getActivity();
    setHasOptionsMenu(true);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_weather;
  }

  @Override
  protected void createGui() {
    m_weatherAdapter = new ArrayAdapter<String>(
        getActivity(),
        R.layout.fake_weather_list_item,
        R.id.weatherLabel,
        new ArrayList<String>()
    );

    m_forecastListView.setAdapter(m_weatherAdapter);

    m_forecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Intent intent = new Intent();
        intent.setClass(m_context, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, m_weatherAdapter.getItem(position));
        m_context.startActivity(intent);
      }
    });

    fetchWeatherInfo();
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
