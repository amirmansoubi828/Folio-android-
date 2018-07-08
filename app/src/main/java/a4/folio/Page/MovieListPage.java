package a4.folio.Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import a4.folio.Adapter.MoviesAdapter;
import a4.folio.ConnectionManager;
import a4.folio.DataType.Movie;
import a4.folio.Listeners.MovieListPageDataListener;
import a4.folio.R;

/**
 * Created by amir on 7/8/2018.
 */

public class MovieListPage extends AppCompatActivity {
    private ListView listView;
    private List<Movie> movieList;
    private ConnectionManager connectionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list_page);
        listView = (ListView) findViewById(R.id.listView_moviesListPage);
        connectionManager = new ConnectionManager();
        connectionManager.setMovieListPageDataListener(new MovieListPageDataListener() {
            @Override
            public void onDataLoaded(final List<Movie> movies) {
                movieList = movies;
                listView.setAdapter(new MoviesAdapter(getApplicationContext(), movies));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String link = movieList.get(position).getLink();
                        Intent intent = new Intent(MovieListPage.this, VideoPlayerPage.class);
                        intent.putExtra("url", link);
                        startActivity(intent);
                    }
                });
            }
        });
        connectionManager.requestMoviesPageInfo();


    }
}
