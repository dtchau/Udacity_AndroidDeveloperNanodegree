package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.adapter.spotify;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.SpotifyStreamerApplication;

import java.util.List;

import javax.inject.Inject;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
public class ArtistArrayAdapter extends ArrayAdapter<Artist> {

  @Inject
  ArtistTrackViewBuilder m_listItemViewBuilder;

  public ArtistArrayAdapter(Context context, List<Artist> artists) {
    super(context, -1, artists);
    ((SpotifyStreamerApplication) getContext().getApplicationContext()).getComponent().inject(this);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (position < 0 || position >= getCount()) {
      return convertView;
    }
    Artist artist = getItem(position);
    if (artist == null) {
      return convertView;
    }
    return m_listItemViewBuilder.build(parent, artist);
  }
}
