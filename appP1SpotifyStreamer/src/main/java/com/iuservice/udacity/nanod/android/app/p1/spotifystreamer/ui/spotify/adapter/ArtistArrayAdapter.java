package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.SpotifyStreamerApplication;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.model.ArtistParcel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
public class ArtistArrayAdapter extends ArrayAdapter<ArtistParcel> {

  @Inject
  ArtistTrackViewBuilder m_listItemViewBuilder;

  public ArtistArrayAdapter(Context context, List<ArtistParcel> artists) {
    super(context, -1, artists);
    ((SpotifyStreamerApplication) getContext().getApplicationContext()).getComponent().inject(this);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (position < 0 || position >= getCount()) {
      return convertView;
    }
    ArtistParcel artistParcel = getItem(position);
    if (artistParcel == null) {
      return convertView;
    }
    return m_listItemViewBuilder.build(parent, artistParcel);
  }
}
