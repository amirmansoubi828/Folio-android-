package a4.folio.DataType;

import a4.folio.DataType.Stock;

/**
 * Created by amir on 5/26/2018.
 */

public class PersonalCapital {
    Stock bourse ;
    int number_of_stocks_person_has ;

    public Stock getBourse() {
        return bourse;
    }

    public void setBourse(Stock bourse) {
        this.bourse = bourse;
    }

    public int getNumber_of_stocks_person_has() {
        return number_of_stocks_person_has;
    }

    public void setNumber_of_stocks_person_has(int number_of_stocks_person_has) {
        this.number_of_stocks_person_has = number_of_stocks_person_has;
    }
}
