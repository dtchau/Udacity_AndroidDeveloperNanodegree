package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.adapter.spotify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kaaes.spotify.webapi.android.models.AlbumSimple;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;

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

  private static String getThumbnailUrl(Artist artist) {
    return getThumbnailUrl(artist.images);
  }

  private static String getThumbnailUrl(Track track) {
    AlbumSimple album = track.album;
    if (album == null) {
      return null;
    }

    return getThumbnailUrl(album.images);
  }

  private static String getThumbnailUrl(List<Image> images) {
    if (images == null || images.isEmpty()) {
      return null;
    }

    return images.get(0).url;
  }

  protected View build(ViewGroup parent, Artist artist) {
    return build(parent, getThumbnailUrl(artist), artist.name, String.format("Followers: %d", artist.followers.total));
  }

  protected View build(ViewGroup parent, Track track) {
    AlbumSimple albumSimple = track.album;
    return build(parent, getThumbnailUrl(track), track.name, String.format("Album: %s", albumSimple == null ? "N/A" : albumSimple.name));
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
