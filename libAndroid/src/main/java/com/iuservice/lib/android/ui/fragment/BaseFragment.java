package com.iuservice.lib.android.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
public abstract class BaseFragment extends Fragment {

  private View m_rootView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    m_rootView = inflater.inflate(getLayoutId(), container, false);
    ButterKnife.inject(this, m_rootView);
    createGui(savedInstanceState);
    return m_rootView;
  }

  protected View getRootView() {
    return m_rootView;
  }

  protected abstract int getLayoutId();

  protected abstract void createGui(Bundle savedInstanceState);
}

