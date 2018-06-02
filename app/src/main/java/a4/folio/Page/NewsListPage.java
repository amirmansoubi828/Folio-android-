package a4.folio.Page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import a4.folio.ConnectionManager;
import a4.folio.DataType.News;
import a4.folio.R;

/**
 * Created by amir on 6/2/2018.
 */

public class NewsListPage extends AppCompatActivity {
    ListView listView;
    ArrayList<News> newsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list_page);
        listView = (ListView) findViewById(R.id.listView_newsListPage);
        Toast.makeText(this, R.string.wait_for_response, Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionManager c = new ConnectionManager();
                try {
                    newsList = c.getBourseNews();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BourseNewsAdapter bNA = new BourseNewsAdapter(getApplicationContext(), newsList);
                        listView.setAdapter(bNA);
                    }
                });
            }
        });
    }
}
