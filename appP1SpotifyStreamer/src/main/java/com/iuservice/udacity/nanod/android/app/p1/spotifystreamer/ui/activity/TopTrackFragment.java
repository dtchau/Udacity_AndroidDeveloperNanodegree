package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.collect.ImmutableMap;
import com.iuservice.lib.android.ui.fragment.BaseFragment;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.R;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.SpotifyStreamerApplication;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter.TrackArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
public class TopTrackFragment extends BaseFragment {

  public static final String ARTIST_ID = TopTrackFragment.class.getCanonicalName().concat("ARTIST_ID");
  private static ImmutableMap<String, Object> COUNTRY_OPTION = ImmutableMap.<String, Object>builder().put("country", "CA").build();

  @InjectView(R.id.noTrackFoundTextView)
  TextView m_noTrackFoundTextView;
  @InjectView(R.id.trackListView)
  ListView m_trackListView;
  @Inject
  SpotifyService m_spotifyService;
  private TrackArrayAdapter m_trackArrayAdapter;
  private String m_artistId;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((SpotifyStreamerApplication) getActivity().getApplication()).getComponent().inject(this);
    loadArtistId(savedInstanceState);
  }

  private void loadArtistId(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      Intent intent = getActivity().getIntent();
      if (intent != null) {
        m_artistId = intent.getStringExtra(ARTIST_ID);
      }
    } else {
      m_artistId = savedInstanceState.getString(ARTIST_ID);
    }
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_top_track;
  }

  @Override
  protected void createGui(Bundle savedInstanceState) {
    m_trackArrayAdapter = new TrackArrayAdapter(getActivity(), new ArrayList<Track>());
    m_trackListView.setAdapter(m_trackArrayAdapter);
  }

  @Override
  public void onResume() {
    super.onResume();
    retrieveTopTracks();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putString(ARTIST_ID, m_artistId);
    super.onSaveInstanceState(outState);
  }

  private void retrieveTopTracks() {
    if (m_artistId != null) {
      m_spotifyService.getArtistTopTrack(m_artistId, COUNTRY_OPTION, new Callback<Tracks>() {
        @Override
        public void success(Tracks tracks, Response response) {
          List<Track> trackList = tracks.tracks;
          if (trackList != null && !trackList.isEmpty()) {
            setActivityStatus(true);
            m_trackArrayAdapter.addAll(trackList);
          } else {
            setActivityStatus(false);
          }
        }

        @Override
        public void failure(RetrofitError error) {
          setActivityStatus(false);
        }
      });
    } else {
      setActivityStatus(false);
    }
  }

  private void setActivityStatus(boolean trackFound) {
    m_trackArrayAdapter.clear();
    m_noTrackFoundTextView.setVisibility(trackFound ? View.GONE : View.VISIBLE);
    m_trackListView.setVisibility(trackFound ? View.VISIBLE : View.GONE);
  }
}
