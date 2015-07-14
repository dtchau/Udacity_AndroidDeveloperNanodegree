package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.SpotifyStreamerApplication;

import java.util.List;

import javax.inject.Inject;

import kaaes.spotify.webapi.android.models.Track;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
public class TrackArrayAdapter extends ArrayAdapter<Track> {

  @Inject
  ArtistTrackViewBuilder m_listItemViewBuilder;

  public TrackArrayAdapter(Context context, List<Track> tracks) {
    super(context, -1, tracks);
    ((SpotifyStreamerApplication) getContext().getApplicationContext()).getComponent().inject(this);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (position < 0 || position >= getCount()) {
      return convertView;
    }
    Track track = getItem(position);
    if (track == null) {
      return convertView;
    }
    return m_listItemViewBuilder.build(parent, track);
  }
}
