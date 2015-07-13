package com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iuservice.udacity.nanod.android.app.p1.spotifystreamer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Dương Tâm Châu <dtc@iuservice.com>
 * @date 2015-07-13.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Artist {

  private String artistName;
  private String thumbnailUrl;

  public static class ListAdapter extends ArrayAdapter<Artist> {

    private Context m_context;


    public ListAdapter(Context context, List<Artist> artists) {
      super(context, -1, artists);
      m_context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      return new ViewWrapper(m_context, parent, getItem(position)).getView();
    }

    // I create this wrapper class so that I can use ButterKnife
    static class ViewWrapper {
      @InjectView(R.id.artistThumbnailImageView)
      ImageView m_artistThumbnailIamgeView;
      @InjectView(R.id.artistNameTextView)
      TextView m_artistNameTextView;
      final View m_view;

      protected ViewWrapper(Context context, ViewGroup parent, Artist artist) {
        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        m_view = layoutInflater.inflate(R.layout.view_artist_list_item, parent, false);
        ButterKnife.inject(this, m_view);
        m_artistNameTextView.setText(artist.getArtistName());
        Picasso.with(context).load(artist.getThumbnailUrl()).placeholder(R.drawable.no_image).into(m_artistThumbnailIamgeView);
      }

      protected View getView() {
        return m_view;
      }
    }
  }
}
