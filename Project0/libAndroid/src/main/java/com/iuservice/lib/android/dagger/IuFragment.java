package com.iuservice.lib.android.dagger;


import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-11.
 */
public class IuFragment extends Fragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((IuApplication) getActivity().getApplication()).inject(this);
  }
}
