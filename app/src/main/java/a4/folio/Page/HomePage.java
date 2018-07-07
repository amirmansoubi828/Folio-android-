package a4.folio.Page;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import a4.folio.ApiManger.PersonalCapital;
import a4.folio.ApiManger.PersonalInfo;
import a4.folio.ConnectionManager;
import a4.folio.DataType.Stock;
import a4.folio.Listeners.HomaPageDataListener;
import a4.folio.PageInfo.HomePageInfo;
import a4.folio.PageInfo.StockPageInfo;
import a4.folio.R;

public class HomePage extends AppCompatActivity {
    TextView allMoney, cashMoney, stocksValue, allProfit, yesterdayProfit;
    TextView allMoneyPersian, cashMoneyPersian, stocksValuePersian, allProfitPersian, yesterdayProfitPersian, positivePersian, negativePersian;
    Button goToStockListPage;
    ListView positiveList, negativeList;
    ConnectionManager connectionManager;
    HomePageInfo homePageInfo;
    Typeface typefaceBtitr, typefaceBnazanin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        homePageInfo = new HomePageInfo();

        connectionManager = new ConnectionManager();
        connectionManager.requestHomePageInfo();

        connectionManager.setHomaPageDataListener(new HomaPageDataListener() {
            @Override
            public void onDataLoaded(List<PersonalCapital> personalCapitals, PersonalInfo personalInfo) {
                homePageInfo.setPersonalInfo(personalInfo);
                homePageInfo.setPersonalCapitals(personalCapitals);
                refresh();
            }
        });

        Toast.makeText(this, R.string.wait_for_response, Toast.LENGTH_SHORT).show();

        typefaceBtitr = Typeface.createFromAsset(getApplicationContext().getAssets(), "BTitr.ttf");
        typefaceBnazanin = Typeface.createFromAsset(getApplicationContext().getAssets(), "BNaznnBd.ttf");

        allMoney = (TextView) findViewById(R.id.textView_homePage_AllMoney);
        cashMoney = (TextView) findViewById(R.id.textView_homePage_Cash);
        stocksValue = (TextView) findViewById(R.id.textView_homePage_stocksValue);
        allProfit = (TextView) findViewById(R.id.textView_homePage_AllProfitPercent);
        yesterdayProfit = (TextView) findViewById(R.id.textView_homePage_YesterdayPercent);

        allMoneyPersian = (TextView) findViewById(R.id.textView_homePage_kole_daraei);
        cashMoneyPersian = (TextView) findViewById(R.id.textView_homePage_pool_naghd);
        stocksValuePersian = (TextView) findViewById(R.id.textView_homePage_majmoo);
        allProfitPersian = (TextView) findViewById(R.id.textView_homePage_soode_kol);
        yesterdayProfitPersian = (TextView) findViewById(R.id.textView_homePage_sood_dirooz);
        positivePersian = (TextView) findViewById(R.id.textView_homePage_mosbat);
        negativePersian = (TextView) findViewById(R.id.textView_homePage_manfi);

        allMoneyPersian.setTypeface(typefaceBnazanin);
        cashMoneyPersian.setTypeface(typefaceBnazanin);
        stocksValuePersian.setTypeface(typefaceBnazanin);
        allProfitPersian.setTypeface(typefaceBnazanin);
        yesterdayProfitPersian.setTypeface(typefaceBnazanin);
        positivePersian.setTypeface(typefaceBnazanin);
        negativePersian.setTypeface(typefaceBnazanin);

        goToStockListPage = (Button) findViewById(R.id.Button_homePage_goToStockListPage);
        positiveList = (ListView) findViewById(R.id.listView_homePage_positive_stocks);
        negativeList = (ListView) findViewById(R.id.listView_homePage_negative_stocks);
        allMoney.setTypeface(typefaceBtitr);
        cashMoney.setTypeface(typefaceBtitr);
        stocksValue.setTypeface(typefaceBtitr);
        allProfit.setTypeface(typefaceBtitr);
        yesterdayProfit.setTypeface(typefaceBtitr);
        goToStockListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(HomePage.this, StockListPage.class);
                    intent.putExtra("allMoney", Integer.valueOf(String.valueOf(allMoney.getText())));
                    intent.putExtra("cashMoney", Integer.valueOf(String.valueOf(cashMoney.getText())));
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(HomePage.this, R.string.wait_for_complete, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void refresh() {
        allMoney.setText(String.valueOf(homePageInfo.getAllMoney()));
        cashMoney.setText(String.valueOf(homePageInfo.getCashMoney()));
        stocksValue.setText(String.valueOf(homePageInfo.getStocksValue()));
        allProfit.setText(String.valueOf(homePageInfo.getAllProfit()));
        yesterdayProfit.setText(String.valueOf(homePageInfo.getYesterdayProfit()));
        stocksValue.setText(String.valueOf(homePageInfo.getStocksValue()));
        allMoney.setText(String.valueOf(homePageInfo.getAllMoney()));
        allProfit.setText(String.valueOf(homePageInfo.getAllProfit()));
        yesterdayProfit.setText(String.valueOf(homePageInfo.getYesterdayProfit()));
        positiveList.setAdapter(new PersonalCapitalAdapter(getApplicationContext(), homePageInfo.getPositives(), true));
        negativeList.setAdapter(new PersonalCapitalAdapter(getApplicationContext(), homePageInfo.getNegatives(), false));
        positiveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stock called = homePageInfo.getPositives().get(position);
                StockPageInfo stockPageInfo = new StockPageInfo(called, homePageInfo.getCashMoney(), homePageInfo.getAllMoney());
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
                Stock called = homePageInfo.getNegatives().get(position);
                StockPageInfo stockPageInfo = new StockPageInfo(called, homePageInfo.getCashMoney(), homePageInfo.getAllMoney());
                Intent i = new Intent(HomePage.this, StockPage.class);
                Bundle b = new Bundle();
                b.putSerializable("stockPI", stockPageInfo);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        connectionManager.requestHomePageInfo();
    }


}
