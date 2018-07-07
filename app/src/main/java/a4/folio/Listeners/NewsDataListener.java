package a4.folio.Listeners;

import java.util.List;

import a4.folio.DataType.News;

/**
 * Created by amir on 7/6/2018.
 */

public interface NewsDataListener {
    void onDataLoaded(List<News> newsArrayList);
}
