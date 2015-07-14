package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.SpotifyStreamerApplication;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.model.TrackParcel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
public class TrackArrayAdapter extends ArrayAdapter<TrackParcel> {

  @Inject
  ArtistTrackViewBuilder m_listItemViewBuilder;

  public TrackArrayAdapter(Context context, List<TrackParcel> tracks) {
    super(context, -1, tracks);
    ((SpotifyStreamerApplication) getContext().getApplicationContext()).getComponent().inject(this);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (position < 0 || position >= getCount()) {
      return convertView;
    }
    TrackParcel trackParcel = getItem(position);
    if (trackParcel == null) {
      return convertView;
    }
    return m_listItemViewBuilder.build(parent, trackParcel);
  }
}
