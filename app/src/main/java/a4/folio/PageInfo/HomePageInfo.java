package a4.folio.PageInfo;

import java.util.ArrayList;

import a4.folio.DataType.Stock;

/**
 * Created by amir on 5/26/2018.
 */

public class HomePageInfo {
    private int allMoney , cashMoney , stocksValue , allProfit , yesterdayProfit ;
    private ArrayList<Stock> positives , negatives ;
    public int getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(int allMoney) {
        this.allMoney = allMoney;
    }

    public int getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(int cashMoney) {
        this.cashMoney = cashMoney;
    }

    public int getStocksValue() {
        return stocksValue;
    }

    public void setStocksValue(int stocksValue) {
        this.stocksValue = stocksValue;
    }

    public int getAllProfit() {
        return allProfit;
    }

    public void setAllProfit(int allProfit) {
        this.allProfit = allProfit;
    }

    public int getYesterdayProfit() {
        return yesterdayProfit;
    }

    public void setYesterdayProfit(int yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    public ArrayList<Stock> getPositives() {
        return positives;
    }

    public void setPositives(ArrayList<Stock> positives) {
        this.positives = positives;
    }

    public ArrayList<Stock> getNegatives() {
        return negatives;
    }

    public void setNegatives(ArrayList<Stock> negatives) {
        this.negatives = negatives;
    }
}
