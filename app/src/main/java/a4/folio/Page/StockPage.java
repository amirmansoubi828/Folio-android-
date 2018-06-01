package a4.folio.Page;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import a4.folio.ConnectionManager;
import a4.folio.PageInfo.StockPageInfo;
import a4.folio.R;
import a4.folio.DataType.Stock;

/**
 * Created by amir on 5/27/2018.
 */

public class StockPage extends AppCompatActivity {
    TextView name, namad, mojoodi, gheymatAkharin, taghirAkharin, darsadAkharin, gheymatPayani, taghirPayani, darsadPayani, bishtarin, kamtarin, dafaateMoamele, hajmMoamelat, aghazin, yesterday, arzeshMoamelat, tasirDarShakhes, pe, eps;
    EditText tedadForoush, tedadKharid;
    TextView gheymatKharid, gheymatForoush, cashMoney;
    Button kharid, forush;
    int buyPrice, sellPrice;
    Typeface typeface ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_page);
        name = (TextView) findViewById(R.id.textView_StockPage_name);
        namad = (TextView) findViewById(R.id.textView_StockPage_namad);
        mojoodi = (TextView) findViewById(R.id.textView_StockPage_mojoodi);
        cashMoney = (TextView) findViewById(R.id.textView_StockPage_cashMoney);
        gheymatAkharin = (TextView) findViewById(R.id.textView_StockPage_akharinGheymat);
        taghirAkharin = (TextView) findViewById(R.id.textView_StockPage_AkharinSood);
        darsadAkharin = (TextView) findViewById(R.id.textView_StockPage_AkharinDarsad);
        gheymatPayani = (TextView) findViewById(R.id.textView_StockPage_PayaniGheymat);
        taghirPayani = (TextView) findViewById(R.id.textView_StockPage_PayaniSood);
        darsadPayani = (TextView) findViewById(R.id.textView_StockPage_PayaniDarsad);
        bishtarin = (TextView) findViewById(R.id.textView_StockPage_Bishtarin);
        kamtarin = (TextView) findViewById(R.id.textView_StockPage_Kamtarin);
        dafaateMoamele = (TextView) findViewById(R.id.textView_StockPage_DafaateMoa);
        hajmMoamelat = (TextView) findViewById(R.id.textView_StockPage_HajmMoa);
        aghazin = (TextView) findViewById(R.id.textView_StockPage_Aghazin);
        tasirDarShakhes = (TextView) findViewById(R.id.textView_StockPage_TasirDarShakhes);
        pe = (TextView) findViewById(R.id.textView_StockPage_PE);
        eps = (TextView) findViewById(R.id.textView_StockPage_EPS);
        gheymatKharid = (TextView) findViewById(R.id.textView_StockPage_PriceOfBuy);
        gheymatForoush = (TextView) findViewById(R.id.textView_StockPage_PriceOfCell);
        yesterday = (TextView) findViewById(R.id.textView_StockPage_RoozeGhabl);
        arzeshMoamelat = (TextView) findViewById(R.id.textView_StockPage_ArzeshMoa);
        tedadKharid = (EditText) findViewById(R.id.editText_StockPage_numberOfBuy);
        tedadForoush = (EditText) findViewById(R.id.editText_StockPage_numberOfcell);
        kharid = (Button) findViewById(R.id.Button_StockPage_buy);
        forush = (Button) findViewById(R.id.Button_StockPage_cell);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "BTitr.ttf");
        refresh(prepareStockPageInfo(getIntent()));
    }

    private void refresh(StockPageInfo s) {
        Stock stock = s.getStock();
        cashMoney.setText(String.valueOf(s.getCashMoney()));
        namad.setText(stock.getNamad());
        name.setText(stock.getName());
        mojoodi.setText(String.valueOf(stock.getMojoodi()));
        namad.setText(stock.getNamad());
        gheymatAkharin.setText(stock.getLastest_Amount());
        taghirAkharin.setText(stock.getLastest_Change());
        darsadAkharin.setText("(" + stock.getLastest_Percentage() + "%)");
        gheymatAkharin.setTextColor(chooseColor(stock.getLastest_Change()));
        taghirAkharin.setTextColor(chooseColor(stock.getLastest_Change()));
        darsadAkharin.setTextColor(chooseColor(stock.getLastest_Change()));
        gheymatPayani.setText(stock.getFinal_Amount());
        darsadPayani.setText("(" + stock.getFinal_Persentage() + "%)");
        taghirPayani.setText(stock.getFinal_Change());
        gheymatPayani.setTextColor(chooseColor(stock.getFinal_Change()));
        darsadPayani.setTextColor(chooseColor(stock.getFinal_Change()));
        taghirPayani.setTextColor(chooseColor(stock.getFinal_Change()));
        bishtarin.setText(stock.getMax_V());
        kamtarin.setText(stock.getMin_V());
        dafaateMoamele.setText(stock.getCount_Of_Transaction());
        hajmMoamelat.setText(stock.getVolume());
        aghazin.setText(stock.getStartingPrice());
        tasirDarShakhes.setText(stock.getIndex_Percentage());
        pe.setText(stock.getPE());
        eps.setText(stock.getEPS());
        yesterday.setText(stock.getYesterday());
        arzeshMoamelat.setText(stock.getValue());

        name.setTypeface(typeface);
        namad.setTypeface(typeface);
        pe.setTypeface(typeface);
        eps.setTypeface(typeface);
        tasirDarShakhes.setTypeface(typeface);
        aghazin.setTypeface(typeface);
        hajmMoamelat.setTypeface(typeface);
        gheymatAkharin.setTypeface(typeface);
        taghirAkharin.setTypeface(typeface);
        darsadAkharin.setTypeface(typeface);
        gheymatPayani.setTypeface(typeface);
        taghirPayani.setTypeface(typeface);
        darsadPayani.setTypeface(typeface);
        mojoodi.setTypeface(typeface);
        dafaateMoamele.setTypeface(typeface);
        kamtarin.setTypeface(typeface);
        bishtarin.setTypeface(typeface);
        cashMoney.setTypeface(typeface);
        arzeshMoamelat.setTypeface(typeface);
        yesterday.setTypeface(typeface);



        int maxV = Integer.parseInt(String.valueOf(stock.getMax_V()).replaceAll(",", ""));
        int minV = Integer.parseInt(String.valueOf(stock.getMin_V()).replaceAll(",", ""));
        int diff = (maxV - minV) / 4;
        buyPrice = maxV - diff;
        sellPrice = minV + diff;
        forush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sell();
            }
        });
        kharid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy();
            }
        });
        gheymatKharid.setText(String.valueOf((int) buyPrice));
        gheymatForoush.setText(String.valueOf((int) sellPrice));

        tedadForoush.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!String.valueOf(s).equals("")) {
                    gheymatForoush.setText(String.valueOf((int) sellPrice * Integer.valueOf(String.valueOf(s))));
                } else {
                    gheymatForoush.setText(String.valueOf((int) sellPrice));
                }
            }
        });
        tedadKharid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!String.valueOf(s).equals("")) {
                    gheymatKharid.setText(String.valueOf((int) buyPrice * Integer.valueOf(String.valueOf(s))));
                } else {
                    gheymatKharid.setText(String.valueOf((int) buyPrice));
                }
            }
        });

    }

    private StockPageInfo prepareStockPageInfo(Intent i) {
        return (StockPageInfo) i.getExtras().getSerializable("stockPI");
    }

    private void sell() {
        final int numberOfSell = Integer.valueOf(String.valueOf(tedadForoush.getText()));
        final int oldAmount  = Integer.valueOf(String.valueOf(mojoodi.getText()));
        final Double cash = Double.valueOf(String.valueOf(cashMoney.getText()));
        if (numberOfSell <= oldAmount) {
            Toast.makeText(this, "صبر کنید ...", Toast.LENGTH_SHORT).show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ConnectionManager c = new ConnectionManager();
                    if (c.trade(String.valueOf(namad.getText()), oldAmount - numberOfSell, (int) (cash + (numberOfSell * sellPrice)))) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cashMoney.setText(String.valueOf(cash + (numberOfSell * sellPrice)));
                                mojoodi.setText(String.valueOf(oldAmount - numberOfSell));
                            }
                        });
                    }//else ...
                }
            });
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(thread);
        } else {
            Toast.makeText(this, "این تعداد از سهم موجود نیست.", Toast.LENGTH_SHORT).show();
        }

    }

    private void buy() {
        final int numberOfBuy = Integer.valueOf(String.valueOf(tedadKharid.getText()));
        final int oldAmount = Integer.valueOf(String.valueOf(mojoodi.getText()));
        final double cash = Double.valueOf(String.valueOf(cashMoney.getText()));
        if ((numberOfBuy * buyPrice) <= cash) {
            Toast.makeText(this, "صبر کنید ...", Toast.LENGTH_SHORT).show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ConnectionManager c = new ConnectionManager();
                    if(c.trade(String.valueOf(namad.getText()), oldAmount + numberOfBuy, (int) (cash - (numberOfBuy * buyPrice)))){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cashMoney.setText(String.valueOf(cash - (numberOfBuy * buyPrice)));
                            mojoodi.setText(String.valueOf(oldAmount + numberOfBuy));
                        }
                    });}//else...
                }
            });
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(thread);
        } else {
            Toast.makeText(this, "پول نقد شما کافی نیست.", Toast.LENGTH_SHORT).show();
        }
    }

    private int chooseColor(String text) {
        if (Integer.valueOf(text.replaceAll(",", "")) < 0) {
            return Color.RED;
        } else {
            return Color.GREEN;
        }
    }


}
