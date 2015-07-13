package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer;

import android.widget.ListView;

import com.iuservice.lib.android.ui.fragment.BaseFragment;
import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.model.Artist;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {

  @InjectView(R.id.artistListView)
  ListView m_artistListView;
  private Artist.ListAdapter m_artistListAdapter;

  protected int getLayoutId() {
    return R.layout.fragment_main;
  }

  @Override
  protected void createGui() {
    List<Artist> artists = new ArrayList<>(10);
    for (int i = 0; i < 10; ++i) {
      artists.add(new Artist("Artist #" + i, "abc"));
    }
    m_artistListAdapter = new Artist.ListAdapter(getActivity(), artists);
    m_artistListView.setAdapter(m_artistListAdapter);
  }
}
