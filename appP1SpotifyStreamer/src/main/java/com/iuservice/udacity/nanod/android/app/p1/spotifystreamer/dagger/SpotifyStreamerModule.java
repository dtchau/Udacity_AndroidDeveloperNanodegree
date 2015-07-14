package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.dagger;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
@Module
public class SpotifyStreamerModule {

  private Application m_application;

  public SpotifyStreamerModule(Application application) {
    m_application = application;
  }

  @Singleton
  @Provides
  Picasso providePicasso() {
    return new Picasso.Builder(m_application).listener(new Picasso.Listener() {
      @Override
      public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
        Log.e("Error loading image", String.format("Can't load '%s'", uri.toString(), exception));
      }
    }).build();
  }

  @Singleton
  @Provides
  SpotifyApi provideSpotifyApi() {
    return new SpotifyApi();
  }

  @Singleton
  @Provides
  SpotifyService provideSpotifyService(SpotifyApi spotifyApi) {
    return spotifyApi.getService();
  }
}
