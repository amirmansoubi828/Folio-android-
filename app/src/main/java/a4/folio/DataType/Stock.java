package a4.folio.DataType;

import java.io.Serializable;

/**
 * Created by amir on 5/26/2018.
 */

public class Stock implements Serializable {
    private String namad, name , market , volume , value
            , count_Of_Transaction , max_V , min_V
            , final_Amount , final_Change , lastest_Change , lastest_Amount ,
            startingPrice , yesterday , PE , EPS
            , stock_Market_Value , best_Supply , best_Demand ,
            final_Persentage , lastest_Percentage , index_Percentage ;
    private int mojoodi;

    public int getMojoodi() {
        return mojoodi;
    }

    public void setMojoodi(int mojoodi) {
        this.mojoodi = mojoodi;
    }

    public Stock(){
    }

    public String getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(String startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getNamad() {
        return namad;
    }

    public void setNamad(String namad) {
        this.namad = namad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCount_Of_Transaction() {
        return count_Of_Transaction;
    }

    public void setCount_Of_Transaction(String count_Of_Transaction) {
        this.count_Of_Transaction = count_Of_Transaction;
    }

    public String getMax_V() {
        return max_V;
    }

    public void setMax_V(String max_V) {
        this.max_V = max_V;
    }

    public String getMin_V() {
        return min_V;
    }

    public void setMin_V(String min_V) {
        this.min_V = min_V;
    }

    public String getFinal_Amount() {
        return final_Amount;
    }

    public void setFinal_Amount(String final_Amount) {
        this.final_Amount = final_Amount;
    }

    public String getFinal_Change() {
        return final_Change;
    }

    public void setFinal_Change(String final_Change) {
        this.final_Change = final_Change;
    }

    public String getLastest_Change() {
        return lastest_Change;
    }

    public void setLastest_Change(String lastest_Change) {
        this.lastest_Change = lastest_Change;
    }

    public String getLastest_Amount() {
        return lastest_Amount;
    }

    public void setLastest_Amount(String lastest_Amount) {
        this.lastest_Amount = lastest_Amount;
    }

    public String getYesterday() {
        return yesterday;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }

    public String getPE() {
        return PE;
    }

    public void setPE(String PE) {
        this.PE = PE;
    }

    public String getEPS() {
        return EPS;
    }

    public void setEPS(String EPS) {
        this.EPS = EPS;
    }

    public String getStock_Market_Value() {
        return stock_Market_Value;
    }

    public void setStock_Market_Value(String stock_Market_Value) {
        this.stock_Market_Value = stock_Market_Value;
    }

    public String getBest_Supply() {
        return best_Supply;
    }

    public void setBest_Supply(String best_Supply) {
        this.best_Supply = best_Supply;
    }

    public String getBest_Demand() {
        return best_Demand;
    }

    public void setBest_Demand(String best_Demand) {
        this.best_Demand = best_Demand;
    }

    public String getFinal_Persentage() {
        return final_Persentage;
    }

    public void setFinal_Persentage(String final_Persentage) {
        this.final_Persentage = final_Persentage;
    }

    public String getLastest_Percentage() {
        return lastest_Percentage;
    }

    public void setLastest_Percentage(String lastest_Percentage) {
        this.lastest_Percentage = lastest_Percentage;
    }

    public String getIndex_Percentage() {
        return index_Percentage;
    }

    public void setIndex_Percentage(String index_Percentage) {
        this.index_Percentage = index_Percentage;
    }
}
