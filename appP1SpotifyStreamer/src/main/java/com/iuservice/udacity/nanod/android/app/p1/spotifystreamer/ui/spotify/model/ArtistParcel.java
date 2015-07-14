package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.ui.spotify.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class ArtistParcel implements Parcelable {

  private String artistId;
  private String artistName;
  private String thumbnailUrl;
  private int numberOfFollowers;

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(artistId);
    dest.writeString(artistName);
    dest.writeString(thumbnailUrl);
    dest.writeInt(numberOfFollowers);
  }

  public static final Parcelable.Creator<ArtistParcel> CREATOR
      = new Parcelable.Creator<ArtistParcel>() {
    public ArtistParcel createFromParcel(Parcel in) {
      return new ArtistParcel(in);
    }

    public ArtistParcel[] newArray(int size) {
      return new ArtistParcel[size];
    }
  };

  private ArtistParcel(Parcel in) {
    setArtistId(in.readString());
    setArtistName(in.readString());
    setThumbnailUrl(in.readString());
    setNumberOfFollowers(in.readInt());
  }

  public static List<ArtistParcel> extractArtistParcels(List<Artist> artists) {
    ArrayList<ArtistParcel> artistParcels = new ArrayList<>(artists.size());
    for (Artist artist : artists) {
      artistParcels.add(new ArtistParcel(artist.id, artist.name, getThumbnailUrl(artist), artist.followers.total));
    }
    return artistParcels;
  }

  private static String getThumbnailUrl(Artist artist) {
    List<Image> images = artist.images;
    if (images == null || images.isEmpty()) {
      return null;
    }
    return images.get(0).url;
  }
}
