package a4.folio.Listeners;

import a4.folio.DataType.ResultMessage;

/**
 * Created by amir on 7/9/2018.
 */

public interface ResultListener {
    void onResultReceived(ResultMessage message);
}
