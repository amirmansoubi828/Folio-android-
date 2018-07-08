package a4.folio.Page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import a4.folio.ConnectionManager;
import a4.folio.DataType.Movie;
import a4.folio.Listeners.MovieListPageDataListener;
import a4.folio.R;

/**
 * Created by amir on 7/8/2018.
 */

public class MovieListPage extends AppCompatActivity {
    private ListView listView;
    private ConnectionManager connectionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list_page);
        connectionManager = new ConnectionManager();
        connectionManager.setMovieListPageDataListener(new MovieListPageDataListener() {
            @Override
            public void onDataLoaded(List<Movie> movies) {
                listView.setAdapter(new MoviesAdapter(getApplicationContext(), movies));
            }
        });
        connectionManager.requestMoviesPageInfo();


    }
}
