package a4.folio.Page;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import a4.folio.DataType.Movie;
import a4.folio.R;

/**
 * Created by amir on 7/8/2018.
 */

public class MoviesAdapter extends BaseAdapter {
    private List<Movie> moviesList;
    private LayoutInflater layoutInflater;
    private Typeface bNazanin, bTitr;

    MoviesAdapter(Context context, List<Movie> moviesList) {
        this.moviesList = moviesList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bTitr = Typeface.createFromAsset(context.getAssets(), "BTitr.ttf");
        bNazanin = Typeface.createFromAsset(context.getAssets(), "BNaznnBd.ttf");
    }

    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public Object getItem(int position) {
        return moviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.movies_list_view_cell, null);

        TextView title = (TextView) view.findViewById(R.id.moviesListPage_textView_title);
        TextView date = (TextView) view.findViewById(R.id.moviesListPage_textView_date);
        TextView detail = (TextView) view.findViewById(R.id.moviesListPage_textView_detail);
        TextView length = (TextView) view.findViewById(R.id.moviesListPage_textView_length);

        title.setText(moviesList.get(position).getName());
        date.setText(moviesList.get(position).getDate());
        detail.setText(moviesList.get(position).getDetail());
        length.setText(moviesList.get(position).getLength());

        date.setTypeface(bTitr);
        title.setTypeface(bNazanin);
        detail.setTypeface(bNazanin);
        length.setTypeface(bTitr);

        return view;
    }
}
