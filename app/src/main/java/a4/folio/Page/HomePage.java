package a4.folio.Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import a4.folio.ConnectionManager;
import a4.folio.PageInfo.HomePageInfo;
import a4.folio.PageInfo.StockPageInfo;
import a4.folio.R;
import a4.folio.DataType.Stock;

public class HomePage extends AppCompatActivity {
    TextView allMoney, cashMoney, stocksValue, allProfit, yesterdayProfit;
    Button goToStockListPage;
    ListView positiveList, negativeList;
    ConnectionManager connectionManager;
    HomePageInfo homePageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toast.makeText(this, "در حال دریافت اطلاعات \n صبر کنید ...", Toast.LENGTH_SHORT).show();

        allMoney = (TextView) findViewById(R.id.textView_homePage_AllMoney);
        cashMoney = (TextView) findViewById(R.id.textView_homePage_Cash);
        stocksValue = (TextView) findViewById(R.id.textView_homePage_stocksValue);
        allProfit = (TextView) findViewById(R.id.textView_homePage_AllProfitPercent);
        yesterdayProfit = (TextView) findViewById(R.id.textView_homePage_YesterdayPercent);
        goToStockListPage = (Button) findViewById(R.id.Button_homePage_goToStockListPage);
        positiveList = (ListView) findViewById(R.id.listView_homePage_positive_stocks);
        negativeList = (ListView) findViewById(R.id.listView_homePage_negative_stocks);
        goToStockListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(HomePage.this, StockListPage.class);
                    intent.putExtra("allMoney", Integer.valueOf(String.valueOf(allMoney.getText())));
                    intent.putExtra("cashMoney", Integer.valueOf(String.valueOf(cashMoney.getText())));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    void refresh() throws Exception {
        connectionManager = new ConnectionManager();
        try {
            homePageInfo = connectionManager.getHomepageInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        allMoney.setText(String.valueOf(homePageInfo.getAllMoney()));
        cashMoney.setText(String.valueOf(homePageInfo.getCashMoney()));
        stocksValue.setText(String.valueOf(homePageInfo.getStocksValue()));
        allProfit.setText(String.valueOf(homePageInfo.getAllProfit()));
        yesterdayProfit.setText(String.valueOf(homePageInfo.getYesterdayProfit()));
        // need to complete listviews
        final HomePageInfo finalHomePageInfo = homePageInfo;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                calculateStocksValue(finalHomePageInfo.getPositives(), finalHomePageInfo.getNegatives());
                stocksValue.setText(String.valueOf(homePageInfo.getStocksValue()));
                allMoney.setText(String.valueOf(homePageInfo.getAllMoney()));
                allProfit.setText(String.valueOf(homePageInfo.getAllProfit()));
                yesterdayProfit.setText(String.valueOf(homePageInfo.getYesterdayProfit()));
                positiveList.setAdapter(new PersonalCapitalAdapter(getApplicationContext(), finalHomePageInfo.getPositives(), true));
                negativeList.setAdapter(new PersonalCapitalAdapter(getApplicationContext(), finalHomePageInfo.getNegatives(), false));
                positiveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Stock called = finalHomePageInfo.getPositives().get(position);
                        StockPageInfo stockPageInfo = new StockPageInfo(called, finalHomePageInfo.getCashMoney(), finalHomePageInfo.getAllMoney());
                        Intent i = new Intent(HomePage.this, StockPage.class);
                        Bundle b = new Bundle();
                        b.putSerializable("stockPI", stockPageInfo);
                        i.putExtras(b);
                        startActivity(i);
                    }
                });
                negativeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Stock called = finalHomePageInfo.getNegatives().get(position);
                        StockPageInfo stockPageInfo = new StockPageInfo(called, finalHomePageInfo.getCashMoney(), finalHomePageInfo.getAllMoney());
                        Intent i = new Intent(HomePage.this, StockPage.class);
                        Bundle b = new Bundle();
                        b.putSerializable("stockPI", stockPageInfo);
                        i.putExtras(b);
                        startActivity(i);
                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(runnable);
    }

    private void calculateStocksValue(ArrayList<Stock> positives, ArrayList<Stock> negatives) {
        int stocksValueInt = 0;
        int yesterdayProfitInt = 0;
        for (int i = 0; i < positives.size(); i++) {
            stocksValueInt += (Integer.valueOf(positives.get(i).getFinal_Amount().replaceAll(",", "")) * positives.get(i).getMojoodi());
            yesterdayProfitInt += ((Integer.valueOf(positives.get(i).getFinal_Amount().replaceAll(",", "")) - Integer.valueOf(positives.get(i).getYesterday().replaceAll(",", ""))) * positives.get(i).getMojoodi());
        }
        for (int i = 0; i < negatives.size(); i++) {
            stocksValueInt += (Integer.valueOf(negatives.get(i).getFinal_Amount().replaceAll(",", "")) * negatives.get(i).getMojoodi());
        }
        homePageInfo.setStocksValue(stocksValueInt);
        homePageInfo.setYesterdayProfit(yesterdayProfitInt);
        homePageInfo.setAllMoney(Integer.valueOf(String.valueOf(homePageInfo.getCashMoney())) + stocksValueInt);
        homePageInfo.setAllProfit(homePageInfo.getAllMoney() - 500000);

    }

}
