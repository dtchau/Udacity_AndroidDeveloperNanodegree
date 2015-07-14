package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iuservice.udacity.android.fundamental.app.x1.sunshine.R;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.RootFragment;

import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends RootFragment {

  @InjectView(R.id.text)
  TextView m_textView;
  private String m_detailText;
  private ShareActionProvider m_shareActionProvider;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getActivity().getIntent();
    if (intent != null) {
      m_detailText = intent.getStringExtra(Intent.EXTRA_TEXT);
    } else {
      m_detailText = "No details available.";
    }
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.fragment_detail, menu);
    m_shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
    setShareActionIntent();
  }

  private void setShareActionIntent() {
    final Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_TEXT, String.format("%s #SunshineApp", m_detailText));
    m_shareActionProvider.setShareIntent(shareIntent);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_detail;
  }

  @Override
  protected void createGui() {
    m_textView.setText(m_detailText);
  }
}
