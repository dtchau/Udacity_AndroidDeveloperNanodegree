package com.iuservice.lib.android.dagger.component;

import com.iuservice.lib.android.dagger.IuActivity;
import com.iuservice.lib.android.dagger.IuApplication;
import com.iuservice.lib.android.dagger.IuFragment;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-06-10.
 */
public interface IuApplicationComponent {

  void inject(IuApplication iuApplication);

  void inject(IuActivity iuActivity);

  void inject(IuFragment iuFragment);
}
