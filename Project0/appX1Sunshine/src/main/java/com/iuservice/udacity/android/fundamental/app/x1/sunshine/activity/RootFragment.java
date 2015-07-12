package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author Duong Tam Chau
 * @date 6/13/2015.
 */
public abstract class RootFragment extends Fragment {

  private View m_rootView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    m_rootView = inflater.inflate(getLayoutId(), container, false);
    ButterKnife.inject(this, m_rootView);
    createGui();
    return m_rootView;
  }

  protected View getRootView() {
    return m_rootView;
  }

  protected abstract int getLayoutId();

  protected abstract void createGui();
}
