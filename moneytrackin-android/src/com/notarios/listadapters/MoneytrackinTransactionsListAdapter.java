package com.notarios.listadapters;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.notarios.R;
import com.notarios.model.Project;
import com.notarios.model.Transaction;

public class MoneytrackinTransactionsListAdapter extends BaseAdapter {
	DecimalFormat dec = new DecimalFormat("###.##");
	private List<Transaction> transactions;
    private final LayoutInflater layoutInflater;
    public MoneytrackinTransactionsListAdapter(Context context, List<Transaction> transactions) {
        this.transactions = transactions;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public int getCount() {
        return transactions == null ? 0 : transactions.size();
    }

    public Transaction getItem(int position) {
        return transactions.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, final View convertView, final ViewGroup parent) {
    	View row;
		// We need the layoutinflater to pick up the view from xml
		if (null == convertView) {
			row = layoutInflater.inflate(R.layout.list_item, null);
		} else {
			row = convertView;
		}
		final TwoLineListItem view = (TwoLineListItem) row;
		view.getText1().setText((getItem(position)).getDescription());
		view.getText2().setText(dec.format((getItem(position)).getAmount()));
		return row;
	}
}
