package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.dagger;

import com.iuservice.lib.android.dagger.module.IuApplicationModule;
import com.iuservice.lib.android.dagger.module.IuApplicationModuleDateFormat;
import com.iuservice.lib.android.dagger.module.IuApplicationModuleRetrofit;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.SpotifyStreamerApplication;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.activity.SearchArtistFragment;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.activity.TopTrackFragment;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter.ArtistArrayAdapter;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter.ArtistTrackViewBuilder;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter.TrackArrayAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
@Singleton
@Component(modules = {
    IuApplicationModule.class,
    IuApplicationModuleRetrofit.class,
    IuApplicationModuleDateFormat.class,
    SpotifyStreamerModule.class
})
public interface SpotifyStreamerComponent {

  void inject(SpotifyStreamerApplication spotifyStreamerApplication);

  void inject(SearchArtistFragment searchArtistFragment);

  void inject(TopTrackFragment topTrackFragment);

  void inject(ArtistArrayAdapter artistArrayAdapter);

  void inject(TrackArrayAdapter trackArrayAdapter);

  void inject(ArtistTrackViewBuilder artistTrackViewBuilder);
}
