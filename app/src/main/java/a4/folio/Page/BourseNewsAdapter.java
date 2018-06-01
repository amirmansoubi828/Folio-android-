package a4.folio.Page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import a4.folio.DataType.News;
import a4.folio.R;

/**
 * Created by amir on 6/1/2018.
 */

public class BourseNewsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<News> newsList;
    private LayoutInflater layoutInflater;

    BourseNewsAdapter(Context context, ArrayList<News> newsList) {
        this.context = context;
        this.newsList = newsList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.personal_capital_list_view_cell, null);

        TextView title = (TextView) view.findViewById(R.id.bourse_news_textView_title);
        TextView date = (TextView) view.findViewById(R.id.bourse_news_textView_date);
        TextView quote = (TextView) view.findViewById(R.id.bourse_news_textView_quote);

        title.setText(newsList.get(position).getTitle());
        date.setText(newsList.get(position).getNews_date());
        quote.setText(newsList.get(position).getNews_Quote());

        return view;
    }
}