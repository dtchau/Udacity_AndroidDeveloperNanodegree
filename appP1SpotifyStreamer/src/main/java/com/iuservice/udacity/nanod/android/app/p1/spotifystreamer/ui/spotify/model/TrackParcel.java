package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-14.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class TrackParcel implements Parcelable {

  private String trackId;
  private String trackName;
  private String albumName;
  private String thumbnailUrl;
  private String previewUrl;

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(trackId);
    dest.writeString(trackName);
    dest.writeString(albumName);
    dest.writeString(thumbnailUrl);
    dest.writeString(previewUrl);
  }

  public static final Parcelable.Creator<TrackParcel> CREATOR
      = new Parcelable.Creator<TrackParcel>() {
    public TrackParcel createFromParcel(Parcel in) {
      return new TrackParcel(in);
    }

    public TrackParcel[] newArray(int size) {
      return new TrackParcel[size];
    }
  };

  private TrackParcel(Parcel in) {
    setTrackId(in.readString());
    setTrackName(in.readString());
    setAlbumName(in.readString());
    setThumbnailUrl(in.readString());
    setPreviewUrl(in.readString());
  }

  public static List<TrackParcel> extractTrackParcels(List<Track> tracks) {
    ArrayList<TrackParcel> trackParcels = new ArrayList<>(tracks.size());
    for (Track track : tracks) {
      trackParcels.add(new TrackParcel(track.id, track.name, getAlbumName(track), getThumbnailUrl(track), track.preview_url));
    }
    return trackParcels;
  }

  private static String getAlbumName(Track track) {
    return track.album == null ? "N/A" : track.album.name;
  }

  private static String getThumbnailUrl(Track track) {
    if (track.album == null) {
      return null;
    }
    List<Image> images = track.album.images;
    if (images == null || images.isEmpty()) {
      return null;
    }
    return images.get(0).url;
  }
}
