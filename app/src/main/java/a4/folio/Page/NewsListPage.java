package a4.folio.Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import a4.folio.ConnectionManager;
import a4.folio.DataType.News;
import a4.folio.Listeners.NewsDataListener;
import a4.folio.R;

/**
 * Created by amir on 6/2/2018.
 */

public class NewsListPage extends AppCompatActivity {
    ListView listView;
    List<News> newsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list_page);
        listView = (ListView) findViewById(R.id.listView_newsListPage);
        Toast.makeText(this, R.string.wait_for_response, Toast.LENGTH_SHORT).show();
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.requestNewsPageInfo();
        connectionManager.setNewsDataListener(new NewsDataListener() {
            @Override
            public void onDataLoaded(List<News> news) {
                newsList = news;
                refresh();
            }
        });

    }

    private void refresh() {
        listView.setAdapter(new BourseNewsAdapter(getApplicationContext(), newsList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewsListPage.this, NewsPage.class);
                intent.putExtra("news", newsList.get(position));
                startActivity(intent);
            }
        });
    }
}
