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
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import a4.folio.ApiManger.PersonalCapital;
import a4.folio.Listeners.BourseInfoDateListener;
import a4.folio.ConnectionManager;
import a4.folio.DataType.Stock;
import a4.folio.PageInfo.StockPageInfo;
import a4.folio.R;

/**
 * Created by amir on 5/26/2018.
 */

public class StockListPage extends AppCompatActivity {
    LinearLayout nameKamel, mojoodi, name, hajm, arzesh, dafmoa, bish, kam, meghpa, taghpa, darpa, meghakh, taghakh, darakh, rooz, tasir, pe, eps, beharz, behtagh;
    int turn = 1;
    Typeface typefaceBTitr;
    static int cashMoney, allMoney;
    HorizontalScrollView scrollView;
    ProgressDialog dialog;
    ConnectionManager connectionManager;
    List<Stock> bourseInformation;
    Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.stock_list_page);
        connectionManager = new ConnectionManager();

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("در حال دریافت اطلاعات\nصبر کنید ...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Toast.makeText(this, R.string.wait, Toast.LENGTH_SHORT).show();
        typefaceBTitr = Typeface.createFromAsset(getApplicationContext().getAssets(), "BTitr.ttf");
        mojoodi = (LinearLayout) findViewById(R.id.mojoodi_layout);
        //stockListPage = (LinearLayout) findViewById(R.id.stockListPage_linearLayout);
        name = (LinearLayout) findViewById(R.id.name_layout);
        nameKamel = (LinearLayout) findViewById(R.id.nameKamel_layout);
        hajm = (LinearLayout) findViewById(R.id.hajm_layout);
        arzesh = (LinearLayout) findViewById(R.id.arzesh_layout);
        dafmoa = (LinearLayout) findViewById(R.id.dafmoa_layout);
        bish = (LinearLayout) findViewById(R.id.bish_layout);
        kam = (LinearLayout) findViewById(R.id.kam_layout);
        meghpa = (LinearLayout) findViewById(R.id.meghpa_layout);
        taghpa = (LinearLayout) findViewById(R.id.taghipa_layout);
        darpa = (LinearLayout) findViewById(R.id.darsdpa_layout);
        meghakh = (LinearLayout) findViewById(R.id.meghdarakh_layout);
        taghakh = (LinearLayout) findViewById(R.id.taghakh_layout);
        darakh = (LinearLayout) findViewById(R.id.darsadakh_layout);
        rooz = (LinearLayout) findViewById(R.id.rooz_layout);
        tasir = (LinearLayout) findViewById(R.id.tasir_layout);
        pe = (LinearLayout) findViewById(R.id.pe_layout);
        eps = (LinearLayout) findViewById(R.id.eps_layout);
        beharz = (LinearLayout) findViewById(R.id.beharz_layout);
        behtagh = (LinearLayout) findViewById(R.id.behtagh_layout);
        scrollView = (HorizontalScrollView) findViewById(R.id.ScrollView_stockListPage);

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
                refresh();
            }
        });
        connectionManager.requestStockListPageInfo();

    }

    private void refresh() {
        for (Stock s : bourseInformation) {
            addStockToList(s);
        }
        scrollView.fullScroll(View.FOCUS_RIGHT);
        dialog.dismiss();

    }

    private void addStockToList(final Stock stock) {
        turn *= -1;


        int colorP = chooseColor(stock.getFinal_Change());
        int colorA = chooseColor(stock.getLastest_Change());
        TextView t = createTextView(stock.getNamad());
        t.setTextColor(colorP);
        t.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StockPageInfo stockPageInfo = new StockPageInfo(stock, cashMoney, allMoney);
                Intent i = new Intent(StockListPage.this, StockPage.class);
                Bundle b = new Bundle();
                b.putSerializable("stockPI", stockPageInfo);
                i.putExtras(b);
                startActivity(i);
            }
        });
        name.addView(t);
        mojoodi.addView(createTextView(stock.getMojoodi()));
        nameKamel.addView(createTextView(stock.getName()));
        hajm.addView(createTextView(stock.getVolume()));
        arzesh.addView(createTextView(stock.getValue()));
        dafmoa.addView(createTextView(stock.getCount_Of_Transaction()));
        bish.addView(createTextView(stock.getMax_V()));
        kam.addView(createTextView(stock.getMin_V()));
        meghpa.addView(createTextView(stock.getFinal_Amount(), colorP));
        taghpa.addView(createTextView(stock.getFinal_Change(), colorP));
        darpa.addView(createTextView(stock.getFinal_Persentage(), colorP));
        meghakh.addView(createTextView(stock.getLastest_Amount(), colorA));
        taghakh.addView(createTextView(stock.getLastest_Change(), colorA));
        darakh.addView(createTextView(stock.getLastest_Percentage(), colorA));
        rooz.addView(createTextView(stock.getYesterday()));
        tasir.addView(createTextView(stock.getIndex_Percentage()));
        pe.addView(createTextView(stock.getPE()));
        eps.addView(createTextView(stock.getEPS()));
        beharz.addView(createTextView(stock.getBest_Supply()));
        behtagh.addView(createTextView(stock.getBest_Demand()));

    }

    private TextView createTextView(Object text) {
        return createTextView(text, getResources().getColor(R.color.colorPrimaryDark));
    }

    private TextView createTextView(Object text, int textColor) {
        String sText = String.valueOf(text);
        TextView textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                120));
        textView.setText(sText);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(textColor);
        textView.setTypeface(typefaceBTitr);
        if (turn == -1) {
            textView.setBackgroundColor(getResources().getColor(R.color.font2));
        }

        return textView;
    }

    private int chooseColor(String text) {
        if (Integer.valueOf(text.replaceAll(",", "")) < 0) {
            return Color.RED;
        } else {
            return Color.GREEN;
        }
    }

    

}
