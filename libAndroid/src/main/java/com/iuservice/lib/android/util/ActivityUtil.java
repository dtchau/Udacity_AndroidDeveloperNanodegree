package com.iuservice.lib.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author Duong Tam Chau
 * @date 6/14/2015.
 */
public class ActivityUtil {

  public static void startActivity(Context context, Class<? extends Activity> activityClass) {
    startActivity(context, activityClass, new Intent());
  }

  public static void startActivity(Context context, Class<? extends Activity> activityClass, Intent intent) {
    intent.setClass(context, activityClass);
    context.startActivity(intent);
  }

  public static void startActionView(Context context, Uri uri) throws NoHandlerAvailableException {
    final Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(uri);
    if (intent.resolveActivity(context.getPackageManager()) != null) {
      context.startActivity(intent);
    } else {
      throw new NoHandlerAvailableException("No handler available! You might need to install extra application to support this action!");
    }
  }

  public static class NoHandlerAvailableException extends Exception {
    public NoHandlerAvailableException() {
      super();
    }

    public NoHandlerAvailableException(String detailMessage) {
      super(detailMessage);
    }

    public NoHandlerAvailableException(String detailMessage, Throwable throwable) {
      super(detailMessage, throwable);
    }

    public NoHandlerAvailableException(Throwable throwable) {
      super(throwable);
    }
  }
}
