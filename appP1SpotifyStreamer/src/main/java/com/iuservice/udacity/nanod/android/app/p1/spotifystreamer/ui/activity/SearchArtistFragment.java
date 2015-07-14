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
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.adapter.spotify.ArtistArrayAdapter;

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

  @InjectView(R.id.noArtistFoundTextView)
  TextView m_noArtistFoundTextView;
  @InjectView(R.id.searchEditText)
  EditText m_searchEditText;
  @InjectView(R.id.artistListView)
  ListView m_artistListView;
  @Inject
  SpotifyService m_spotifyService;
  private ArtistArrayAdapter m_artistListAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((SpotifyStreamerApplication) getActivity().getApplication()).getComponent().inject(this);
  }

  void loadArtistSearchTerm(Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      m_searchEditText.setText(savedInstanceState.getString(ARTIST_QUERY_TERM));
    }
  }

  protected int getLayoutId() {
    return R.layout.fragment_search_artist;
  }

  @Override
  protected void createGui(Bundle savedInstanceState) {
    m_artistListAdapter = new ArtistArrayAdapter(getActivity(), new ArrayList<Artist>());
    m_artistListView.setAdapter(m_artistListAdapter);
    loadArtistSearchTerm(savedInstanceState);
  }

  @OnTextChanged(value = R.id.searchEditText)
  void onSearchEditTextChanged(CharSequence text) {
    m_spotifyService.searchArtists(text.toString(), new Callback<ArtistsPager>() {
      @Override
      public void success(ArtistsPager artistsPager, Response response) {
        List<Artist> artists = artistsPager.artists.items;
        if (artists != null && !artists.isEmpty()) {
          setActivityStatus(true);
          m_artistListAdapter.addAll(artistsPager.artists.items);
        } else {
          setActivityStatus(false);
        }
      }

      @Override
      public void failure(RetrofitError error) {
        setActivityStatus(false);
      }
    });
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putString(ARTIST_QUERY_TERM, m_searchEditText.getText().toString());
    super.onSaveInstanceState(outState);
  }

  private void setActivityStatus(boolean artistFound) {
    m_artistListAdapter.clear();
    m_noArtistFoundTextView.setVisibility(artistFound ? View.GONE : View.VISIBLE);
    m_artistListView.setVisibility(artistFound ? View.VISIBLE : View.GONE);
  }

  @OnItemClick(R.id.artistListView)
  void onArtistItemClick(int position) {
    if (position >= 0 && position < m_artistListAdapter.getCount()) {
      Intent intent = new Intent();
      intent.putExtra(TopTrackFragment.ARTIST_ID, m_artistListAdapter.getItem(position).id);
      ActivityUtil.startActivity(getActivity(), TopTrackActivity.class, intent);
    }
  }
}
