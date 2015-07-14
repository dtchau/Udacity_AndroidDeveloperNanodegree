package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer;

import android.app.Application;

import com.iuservice.lib.android.dagger.module.IuApplicationModule;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.dagger.DaggerSpotifyStreamerComponent;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.dagger.SpotifyStreamerComponent;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.dagger.SpotifyStreamerModule;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
public class SpotifyStreamerApplication extends Application {

  private SpotifyStreamerComponent m_component;

  @Override
  public void onCreate() {
    super.onCreate();
    m_component = createApplicationComponent();
  }

  public SpotifyStreamerComponent getComponent() {
    return m_component;
  }

  private SpotifyStreamerComponent createApplicationComponent() {
    return DaggerSpotifyStreamerComponent.builder()
        .iuApplicationModule(new IuApplicationModule(this))
        .spotifyStreamerModule(new SpotifyStreamerModule(this))
        .build();
  }
}
