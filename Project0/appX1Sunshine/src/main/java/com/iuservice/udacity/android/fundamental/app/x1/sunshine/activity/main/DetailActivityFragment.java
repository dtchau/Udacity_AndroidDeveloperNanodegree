package com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.main;

import android.content.Intent;
import android.widget.TextView;

import com.iuservice.udacity.android.fundamental.app.x1.sunshine.R;
import com.iuservice.udacity.android.fundamental.app.x1.sunshine.activity.RootFragment;

import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends RootFragment {

  @InjectView(R.id.text)
  TextView textView;

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_detail;
  }

  @Override
  protected void createGui() {
    Intent intent = getActivity().getIntent();
    if (intent != null) {
      textView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
    } else {
      textView.setText("NO INTENT");
    }
  }
}
