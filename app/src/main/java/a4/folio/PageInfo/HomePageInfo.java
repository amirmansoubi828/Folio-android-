package a4.folio.PageInfo;

import java.util.ArrayList;
import java.util.List;

import a4.folio.ApiManger.PersonalCapital;
import a4.folio.ApiManger.PersonalInfo;
import a4.folio.DataType.Stock;

/**
 * Created by amir on 5/26/2018.
 */

public class HomePageInfo {
    private int allMoney, cashMoney, stocksValue, allProfit, yesterdayProfit;
    private ArrayList<Stock> positives, negatives;

    public void setPersonalInfo(PersonalInfo personalInfo) {
        cashMoney = personalInfo.getMoney();
    }

    public void setPersonalCapitals(List<PersonalCapital> personalCapitals) {
        positives = new ArrayList<Stock>();
        negatives = new ArrayList<Stock>();
        for (int i = 0; i < personalCapitals.size(); i++) {
            PersonalCapital p = personalCapitals.get(i);
            Stock s = p.getBourse();
            s.setMojoodi(p.getNumber_of_stocks_person_has());
            if (Integer.valueOf(s.getLastest_Change()) >= 0) {
                positives.add(s);
            } else {
                negatives.add(s);
            }
        }
        calculateStocksValue();
    }

    public int getAllMoney() {
        return allMoney;
    }

    public int getCashMoney() {
        return cashMoney;
    }

    public int getStocksValue() {
        return stocksValue;
    }

    public int getAllProfit() {
        return allProfit;
    }

    public int getYesterdayProfit() {
        return yesterdayProfit;
    }

    public ArrayList<Stock> getPositives() {
        return positives;
    }

    public ArrayList<Stock> getNegatives() {
        return negatives;
    }

    private void calculateStocksValue() {
        int stocksValueInt = 0;
        int yesterdayProfitInt = 0;
        for (int i = 0; i < positives.size(); i++) {
            stocksValueInt += (Integer.valueOf(positives.get(i).getFinal_Amount().replaceAll(",", "")) * positives.get(i).getMojoodi());
            yesterdayProfitInt += ((Integer.valueOf(positives.get(i).getFinal_Amount().replaceAll(",", "")) - Integer.valueOf(positives.get(i).getYesterday().replaceAll(",", ""))) * positives.get(i).getMojoodi());
        }
        for (int i = 0; i < negatives.size(); i++) {
            stocksValueInt += (Integer.valueOf(negatives.get(i).getFinal_Amount().replaceAll(",", "")) * negatives.get(i).getMojoodi());
        }
        stocksValue = stocksValueInt;
        yesterdayProfit = yesterdayProfitInt;
        allMoney = cashMoney + stocksValueInt;
        allProfit = allMoney - 500000;

    }

}
