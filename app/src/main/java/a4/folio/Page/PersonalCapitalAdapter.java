package a4.folio.Page;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import a4.folio.R;
import a4.folio.DataType.Stock;

/**
 * Created by amir on 5/28/2018.
 */

public class PersonalCapitalAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Stock> stocks;
    private LayoutInflater layoutInflater;
    private boolean pos;

    PersonalCapitalAdapter(Context context, ArrayList<Stock> stocks, boolean isPositive) {
        this.context = context;
        this.stocks = stocks;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        pos = isPositive;
    }

    @Override
    public int getCount() {
        return stocks.size();
    }

    @Override
    public Object getItem(int position) {
        return stocks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.personal_capital_list_view_cell, null);

        TextView namad = (TextView) view.findViewById(R.id.personal_capital_cell_namad);
        TextView mojoodi = (TextView) view.findViewById(R.id.personal_capital_cell_mojoodi);
        TextView ghey = (TextView) view.findViewById(R.id.personal_capital_cell_gheymat);
        TextView tagh = (TextView) view.findViewById(R.id.personal_capital_cell_taghir);
        TextView darsad = (TextView) view.findViewById(R.id.personal_capital_cell_darsad);

        namad.setText(stocks.get(position).getNamad());
        mojoodi.setText(String.valueOf(stocks.get(position).getMojoodi()));
        ghey.setText(stocks.get(position).getLastest_Amount());
        tagh.setText(stocks.get(position).getLastest_Change());
        darsad.setText("(" + stocks.get(position).getLastest_Percentage() + "%)");

        if (pos) {
            ghey.setTextColor(Color.GREEN);
            tagh.setTextColor(Color.GREEN);
            darsad.setTextColor(Color.GREEN);
        } else {

            ghey.setTextColor(Color.RED);
            tagh.setTextColor(Color.RED);
            darsad.setTextColor(Color.RED);
        }


        return view;
    }
}
