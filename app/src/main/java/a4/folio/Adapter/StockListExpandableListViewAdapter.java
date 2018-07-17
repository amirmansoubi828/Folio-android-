package a4.folio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import a4.folio.DataType.Stock;
import a4.folio.Page.StockListPage;
import a4.folio.Page.StockPage;
import a4.folio.PageInfo.StockPageInfo;
import a4.folio.R;


public class StockListExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<Stock> stocks;
    private LayoutInflater layoutInflater;
    private Typeface typeface;
    private Context context;

    public StockListExpandableListViewAdapter(List<Stock> stocks, Context context) {
        this.stocks = stocks;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        typeface = Typeface.createFromAsset(context.getAssets(), "BTitr.ttf");
        this.context = context;

    }

    @Override
    public int getGroupCount() {
        return stocks.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return stocks.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return stocks.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if (view == null) {

            view = layoutInflater.inflate(R.layout.stock_list_page_list_view_cell_group, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.stockListPage_cell_group_textView_symbol);
            viewHolder.mojoodi = (TextView) view.findViewById(R.id.stockListPage_cell_group_textView_mojoodi);
            viewHolder.count = (TextView) view.findViewById(R.id.stockListPage_cell_group_textView_count);
            viewHolder.amount = (TextView) view.findViewById(R.id.stockListPage_cell_group_textView_amount);
            viewHolder.change = (TextView) view.findViewById(R.id.stockListPage_cell_group_textView_change);
            viewHolder.percentage = (TextView) view.findViewById(R.id.stockListPage_cell_group_textView_percent);

            viewHolder.name.setTypeface(typeface);
            viewHolder.mojoodi.setTypeface(typeface);
            viewHolder.count.setTypeface(typeface);
            viewHolder.amount.setTypeface(typeface);
            viewHolder.change.setTypeface(typeface);
            viewHolder.percentage.setTypeface(typeface);

            view.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.name.setText(stocks.get(groupPosition).getNamad());
        viewHolder.mojoodi.setText(String.valueOf(stocks.get(groupPosition).getMojoodi()));
        viewHolder.count.setText(stocks.get(groupPosition).getCount_Of_Transaction());
        viewHolder.amount.setText(stocks.get(groupPosition).getLastest_Amount());
        viewHolder.change.setText(stocks.get(groupPosition).getLastest_Change());
        viewHolder.percentage.setText(stocks.get(groupPosition).getLastest_Percentage());

        Stock stock = stocks.get(groupPosition);
        viewHolder.amount.setTextColor(chooseColor(stock.getLastest_Change()));
        viewHolder.change.setTextColor(chooseColor(stock.getLastest_Change()));
        viewHolder.percentage.setTextColor(chooseColor(stock.getLastest_Change()));

        if (stock.getMojoodi() > 0) {
            viewHolder.name.setTextColor(Color.BLUE);
        } else {
            viewHolder.name.setTextColor(Color.rgb(62, 30, 20));
            viewHolder.mojoodi.setText("");
        }


        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.stock_list_page_list_view_cell_child, null);
        final Stock stock = stocks.get(groupPosition);

        TextView gheymatPayani, taghirPayani, darsadPayani, bishtarin, kamtarin, hajmMoamelat, yesterday, arzeshMoamelat, tasirDarShakhes, pe, eps;
        Button goToStockPage;

        gheymatPayani = (TextView) view.findViewById(R.id.textView_StockPage_PayaniGheymat);
        taghirPayani = (TextView) view.findViewById(R.id.textView_StockPage_PayaniSood);
        darsadPayani = (TextView) view.findViewById(R.id.textView_StockPage_PayaniDarsad);
        bishtarin = (TextView) view.findViewById(R.id.textView_StockPage_Bishtarin);
        kamtarin = (TextView) view.findViewById(R.id.textView_StockPage_Kamtarin);
        hajmMoamelat = (TextView) view.findViewById(R.id.textView_StockPage_HajmMoa);
        tasirDarShakhes = (TextView) view.findViewById(R.id.textView_StockPage_TasirDarShakhes);
        pe = (TextView) view.findViewById(R.id.textView_StockPage_PE);
        eps = (TextView) view.findViewById(R.id.textView_StockPage_EPS);
        yesterday = (TextView) view.findViewById(R.id.textView_StockPage_RoozeGhabl);
        arzeshMoamelat = (TextView) view.findViewById(R.id.textView_StockPage_ArzeshMoa);

        goToStockPage = (Button) view.findViewById(R.id.goToStockPage_StockListPage_child_Button);

        gheymatPayani.setText(stock.getFinal_Amount());
        darsadPayani.setText("(" + stock.getFinal_Persentage() + "%)");
        taghirPayani.setText(stock.getFinal_Change());
        gheymatPayani.setTextColor(chooseColor(stock.getFinal_Change()));
        darsadPayani.setTextColor(chooseColor(stock.getFinal_Change()));
        taghirPayani.setTextColor(chooseColor(stock.getFinal_Change()));
        bishtarin.setText(stock.getMax_V());
        kamtarin.setText(stock.getMin_V());
        hajmMoamelat.setText(stock.getVolume());
        tasirDarShakhes.setText(stock.getIndex_Percentage());
        pe.setText(stock.getPE());
        eps.setText(stock.getEPS());
        yesterday.setText(stock.getYesterday());
        arzeshMoamelat.setText(stock.getValue());

        gheymatPayani.setTypeface(typeface);
        taghirPayani.setTypeface(typeface);
        darsadPayani.setTypeface(typeface);
        kamtarin.setTypeface(typeface);
        bishtarin.setTypeface(typeface);
        arzeshMoamelat.setTypeface(typeface);
        yesterday.setTypeface(typeface);
        pe.setTypeface(typeface);
        eps.setTypeface(typeface);
        tasirDarShakhes.setTypeface(typeface);
        hajmMoamelat.setTypeface(typeface);

        goToStockPage.setTypeface(typeface);
        goToStockPage.setText(goToStockPage.getText().toString().replace("؟", stock.getNamad()));

        goToStockPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StockPage.class);
                StockPageInfo stockPageInfo = new StockPageInfo(stocks.get(groupPosition), StockListPage.getCashMoney(), 0);
                intent.putExtra("stockPI", stockPageInfo);
                context.startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private int chooseColor(String text) {
        try {
            if (Integer.valueOf(text.replaceAll(",", "")) < 0) {
                return Color.RED;
            } else {
                return Color.GREEN;
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return Color.GRAY;
        }
    }
}
