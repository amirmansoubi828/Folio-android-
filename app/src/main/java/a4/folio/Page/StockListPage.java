package a4.folio.Page;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a4.folio.Adapter.StockListExpandableListViewAdapter;
import a4.folio.ConnectionManager;
import a4.folio.DataType.PersonalCapital;
import a4.folio.DataType.Stock;
import a4.folio.Listeners.BourseInfoDateListener;
import a4.folio.PageInfo.StockPageInfo;
import a4.folio.R;

/**
 * Created by amir on 5/26/2018.
 */

public class StockListPage extends AppCompatActivity {

    private Typeface typefaceBTitr;
    private static int cashMoney, allMoney;
    private ProgressDialog dialog;
    private ConnectionManager connectionManager;
    private List<Stock> bourseInformation;
    private ExpandableListView expandableListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_list_page);

        connectionManager = new ConnectionManager();

        expandableListView = (ExpandableListView) findViewById(R.id.stockListPage_listView);

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("در حال دریافت اطلاعات\nصبر کنید ...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        typefaceBTitr = Typeface.createFromAsset(getApplicationContext().getAssets(), "BTitr.ttf");

        allMoney = getIntent().getIntExtra("allMoney", 0);
        cashMoney = getIntent().getIntExtra("cashMoney", 0);

        connectionManager.setBourseInfoDateListener(new BourseInfoDateListener() {
            @Override
            public void onDataLoaded(List<Stock> stocks, List<PersonalCapital> personalCapitals) {
                bourseInformation = stocks;
                for (int i = 0; i < personalCapitals.size(); i++) {
                    int sci = personalCapitals.get(i).getNumber_of_stocks_person_has();
                    Stock scs = personalCapitals.get(i).getBourse();
                    for (int j = 0; j < bourseInformation.size(); j++) {
                        if (bourseInformation.get(j).getNamad().equals(scs.getNamad())) {
                            bourseInformation.get(j).setMojoodi(sci);
                        }
                    }
                }
                StockListExpandableListViewAdapter stockListExpandableListViewAdapter = new StockListExpandableListViewAdapter(bourseInformation, getApplicationContext());
                expandableListView.setAdapter(stockListExpandableListViewAdapter);
                dialog.dismiss();
            }
        });
        connectionManager.requestStockListPageInfo();


    }


    public static int getCashMoney() {
        return cashMoney;
    }

    public static void setCashMoney(int cashMoney) {
        StockListPage.cashMoney = cashMoney;
    }

}
