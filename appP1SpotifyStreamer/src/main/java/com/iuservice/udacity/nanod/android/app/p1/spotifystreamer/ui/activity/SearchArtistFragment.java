package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.iuservice.lib.android.ui.fragment.BaseFragment;
import com.iuservice.lib.android.util.ActivityUtil;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.R;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.SpotifyStreamerApplication;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.adapter.ArtistArrayAdapter;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.model.ArtistParcel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
public class SearchArtistFragment extends BaseFragment {

  private static final String ARTIST_QUERY_TERM = SearchArtistFragment.class.getCanonicalName().concat("ARTIST_QUERY_TERM");
  private static final String ARTIST_LIST = SearchArtistFragment.class.getCanonicalName().concat("ARTIST_LIST");

  @InjectView(R.id.noArtistFoundTextView)
  TextView m_noArtistFoundTextView;
  @InjectView(R.id.searchEditText)
  EditText m_searchEditText;
  @InjectView(R.id.artistListView)
  ListView m_artistListView;
  @Inject
  SpotifyService m_spotifyService;
  private ArrayList<ArtistParcel> m_artistList;
  private ArtistArrayAdapter m_artistListAdapter;
  private String m_lastSearchTerm;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((SpotifyStreamerApplication) getActivity().getApplication()).getComponent().inject(this);
  }

  protected int getLayoutId() {
    return R.layout.fragment_search_artist;
  }

  @Override
  protected void createGui(Bundle savedInstanceState) {
    m_artistList = new ArrayList<>();
    m_artistListAdapter = new ArtistArrayAdapter(getActivity(), m_artistList);
    m_artistListView.setAdapter(m_artistListAdapter);
    loadArtistSearchTerm(savedInstanceState);
  }

  void loadArtistSearchTerm(Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      m_lastSearchTerm = savedInstanceState.getString(ARTIST_QUERY_TERM);
      m_searchEditText.setText(m_lastSearchTerm);
      List<ArtistParcel> artistParcels = savedInstanceState.getParcelableArrayList(ARTIST_LIST);
      if (artistParcels == null || artistParcels.isEmpty()) {
        setActivityStatus(false);
      } else {
        setActivityStatus(true);
        m_artistListAdapter.addAll(artistParcels);
      }
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putString(ARTIST_QUERY_TERM, m_searchEditText.getText().toString());
    outState.putParcelableArrayList(ARTIST_LIST, m_artistList);
    super.onSaveInstanceState(outState);
  }

  private void setActivityStatus(boolean artistFound) {
    m_artistListAdapter.clear();
    m_noArtistFoundTextView.setVisibility(artistFound ? View.GONE : View.VISIBLE);
    m_artistListView.setVisibility(artistFound ? View.VISIBLE : View.GONE);
  }

  @OnTextChanged(R.id.searchEditText)
  void onSearchEditTextChange(CharSequence text) {
    String searchTerm = text.toString().trim();
    if (!searchTerm.equalsIgnoreCase(m_lastSearchTerm)) {
      m_spotifyService.searchArtists(searchTerm, new Callback<ArtistsPager>() {
        @Override
        public void success(ArtistsPager artistsPager, Response response) {
          List<Artist> artists = artistsPager.artists.items;
          if (artists != null && !artists.isEmpty()) {
            setActivityStatus(true);
            m_artistListAdapter.addAll(ArtistParcel.extractArtistParcels(artistsPager.artists.items));
          } else {
            setActivityStatus(false);
          }
        }

        @Override
        public void failure(RetrofitError error) {
          setActivityStatus(false);
        }
      });
      m_lastSearchTerm = null;
    }
  }

  @OnItemClick(R.id.artistListView)
  void onArtistItemClick(int position) {
    if (position >= 0 && position < m_artistListAdapter.getCount()) {
      Intent intent = new Intent();
      ArtistParcel artistParcel = m_artistListAdapter.getItem(position);
      if (artistParcel != null) {
        intent.putExtra(TopTrackFragment.ARTIST_ID, artistParcel.getArtistId());
        intent.putExtra(TopTrackFragment.ARTIST_NAME, artistParcel.getArtistName());
      }
      ActivityUtil.startActivity(getActivity(), TopTrackActivity.class, intent);
    }
  }
}
