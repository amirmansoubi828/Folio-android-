package a4.folio.Listeners;

import java.util.List;

import a4.folio.DataType.PersonalCapital;
import a4.folio.DataType.PersonalInfo;

/**
 * Created by amir on 7/6/2018.
 */

public interface HomaPageDataListener {
    void onDataLoaded(List<PersonalCapital> personalCapitals, PersonalInfo personalInfo);
}
