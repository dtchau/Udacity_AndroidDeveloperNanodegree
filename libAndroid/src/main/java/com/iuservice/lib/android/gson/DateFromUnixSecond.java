package com.iuservice.lib.android.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * @author Duong Tam Chau
 * @date 6/13/2015.
 */
public class DateFromUnixSecond extends Date {

  public DateFromUnixSecond(long milliseconds) {
    super(milliseconds);
  }

  private long toJsonValue() {
    return getTime() / 1000;
  }

  public static class DateFromUnixSecondTypeAdapter extends TypeAdapter<DateFromUnixSecond> {
    @Override
    public void write(JsonWriter out, DateFromUnixSecond value) throws IOException {
      if (value == null) {
        out.nullValue();
        return;
      }

      out.value(value.toJsonValue());
    }

    @Override
    public DateFromUnixSecond read(JsonReader in) throws IOException {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull();
        return null;
      }

      long unixSecond = in.nextLong();
      return new DateFromUnixSecond(unixSecond * 1000);
    }
  }
}
