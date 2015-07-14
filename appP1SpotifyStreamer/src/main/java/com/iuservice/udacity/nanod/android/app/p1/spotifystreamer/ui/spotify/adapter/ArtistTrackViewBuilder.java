package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.R;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.model.ArtistParcel;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.model.TrackParcel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
@Singleton
public class ArtistTrackViewBuilder {

  @InjectView(R.id.thumbnailImageView)
  ImageView m_thumbnailImageView;
  @InjectView(R.id.titleTextView)
  TextView m_titleTextView;
  @InjectView(R.id.statusTextView)
  TextView m_statusTextView;

  private LayoutInflater m_layoutInflater;
  private Picasso m_picasso;

  @Inject
  public ArtistTrackViewBuilder(LayoutInflater layoutInflater, Picasso picasso) {
    m_layoutInflater = layoutInflater;
    m_picasso = picasso;
  }

  protected View build(ViewGroup parent, ArtistParcel artistParcel) {
    return build(parent, artistParcel.getThumbnailUrl(), artistParcel.getArtistName(), String.format("Followers: %d", artistParcel.getNumberOfFollowers()));
  }

  protected View build(ViewGroup parent, TrackParcel trackParcel) {
    return build(parent, trackParcel.getThumbnailUrl(), trackParcel.getTrackName(), String.format("Album: %s", trackParcel.getAlbumName()));
  }

  private View build(ViewGroup parent, String thumbnailUrl, String title, String status) {
    final View view = m_layoutInflater.inflate(R.layout.list_item_artist_track, parent, false);
    ButterKnife.inject(this, view);
    m_picasso.load(thumbnailUrl).placeholder(R.drawable.no_image).into(m_thumbnailImageView);
    m_titleTextView.setText(title);
    m_statusTextView.setText(status);
    return view;
  }
}
