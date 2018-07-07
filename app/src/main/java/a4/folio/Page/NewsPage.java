package a4.folio.Page;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import a4.folio.DataType.News;
import a4.folio.R;

/**
 * Created by amir on 6/2/2018.
 */

public class NewsPage extends AppCompatActivity {
    TextView date, title, description;
    News news;
    private Typeface bNazanin, bTitr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_page);

        news = (News) getIntent().getSerializableExtra("news");

        date = (TextView) findViewById(R.id.newsPage_textView_date);
        title = (TextView) findViewById(R.id.newsPage_textView_Title);
        description = (TextView) findViewById(R.id.newsPage_textView_description);

        date.setText(news.getNews_date());
        title.setText(news.getTitle());
        description.setText(news.getNews_Body());

        bTitr = Typeface.createFromAsset(getApplicationContext().getAssets(), "BTitr.ttf");
        bNazanin = Typeface.createFromAsset(getApplicationContext().getAssets(), "BNaznnBd.ttf");

        date.setTypeface(bTitr);
        title.setTypeface(bNazanin);
        description.setTypeface(bNazanin);

    }
}
