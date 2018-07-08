package a4.folio.Listeners;

import java.util.List;

import a4.folio.DataType.PersonalCapital;
import a4.folio.DataType.Stock;

/**
 * Created by amir on 7/6/2018.
 */

public interface BourseInfoDateListener {
    void onDataLoaded(List<Stock> stocks , List<PersonalCapital> personalCapitals);
}
