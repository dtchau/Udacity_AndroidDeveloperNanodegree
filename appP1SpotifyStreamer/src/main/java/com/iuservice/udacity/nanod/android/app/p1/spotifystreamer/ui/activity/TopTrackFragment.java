package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.collect.ImmutableMap;
import com.iuservice.lib.android.ui.fragment.BaseFragment;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.R;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.SpotifyStreamerApplication;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter.TrackArrayAdapter;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.model.TrackParcel;

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
  public static final String ARTIST_NAME = TopTrackFragment.class.getCanonicalName().concat("ARTIST_NAME");
  public static final String TRACK_LIST = TopTrackFragment.class.getCanonicalName().concat("TRACK_LIST");
  private static ImmutableMap<String, Object> COUNTRY_OPTION = ImmutableMap.<String, Object>builder().put("country", "CA").build();

  @InjectView(R.id.noTrackFoundTextView)
  TextView m_noTrackFoundTextView;
  @InjectView(R.id.trackListView)
  ListView m_trackListView;
  @Inject
  SpotifyService m_spotifyService;
  private TrackArrayAdapter m_trackArrayAdapter;
  private ArrayList<TrackParcel> m_trackList;
  private String m_artistId;
  private String m_artistName;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((SpotifyStreamerApplication) getActivity().getApplication()).getComponent().inject(this);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_top_track;
  }

  @Override
  protected void createGui(Bundle savedInstanceState) {
    m_trackList = new ArrayList<>();
    m_trackArrayAdapter = new TrackArrayAdapter(getActivity(), m_trackList);
    m_trackListView.setAdapter(m_trackArrayAdapter);
    loadArtistId(savedInstanceState);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putString(ARTIST_ID, m_artistId);
    outState.putString(ARTIST_NAME, m_artistName);
    outState.putParcelableArrayList(TRACK_LIST, m_trackList);
    super.onSaveInstanceState(outState);
  }

  private void loadArtistId(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      Intent intent = getActivity().getIntent();
      if (intent != null) {
        m_artistId = intent.getStringExtra(ARTIST_ID);
        m_artistName = intent.getStringExtra(ARTIST_NAME);
      }
      retrieveTopTracks();
    } else {
      m_artistId = savedInstanceState.getString(ARTIST_ID);
      m_artistName = savedInstanceState.getString(ARTIST_NAME);
      List<TrackParcel> trackParcels = savedInstanceState.getParcelableArrayList(TRACK_LIST);
      if (trackParcels == null || trackParcels.isEmpty()) {
        setActivityStatus(false);
      } else {
        setActivityStatus(true);
        m_trackArrayAdapter.addAll(trackParcels);
      }
    }
    ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(m_artistName);
  }

  private void retrieveTopTracks() {
    if (m_artistId != null) {
      m_spotifyService.getArtistTopTrack(m_artistId, COUNTRY_OPTION, new Callback<Tracks>() {
        @Override
        public void success(Tracks tracks, Response response) {
          List<Track> trackList = tracks.tracks;
          if (trackList != null && !trackList.isEmpty()) {
            setActivityStatus(true);
            m_trackArrayAdapter.addAll(TrackParcel.extractTrackParcels(trackList));
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
