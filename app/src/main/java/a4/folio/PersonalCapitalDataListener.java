package a4.folio;

import java.util.List;

import a4.folio.ApiManger.PersonalCapital;

/**
 * Created by amir on 7/6/2018.
 */

public interface PersonalCapitalDataListener {
    void onDataLoaded(List<PersonalCapital> personalCapitals);
}
