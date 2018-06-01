package a4.folio.PageInfo;

import java.io.Serializable;

import a4.folio.DataType.Stock;

/**
 * Created by amir on 5/27/2018.
 */

public class StockPageInfo implements Serializable {
    private Stock stock ;
    private int cashMoney , allMoney;

    public StockPageInfo(Stock stock, int cashMoney, int allMoney) {
        this.stock = stock;
        this.cashMoney = cashMoney;
        this.allMoney = allMoney;
    }

    public double getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(int allMoney) {
        this.allMoney = allMoney;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(int cashMoney) {
        this.cashMoney = cashMoney;
    }
}
