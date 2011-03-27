/*
 * ActivityProject.java March 2011
 *
 * Copyright (C) 2011, 
 * 		Nicolás Notario McDonnell <nicolasnm@gmail.com>
 *      Roberto Notario McDonnell <zanaskw@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package com.notarios;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.notarios.bo.impl.TransactionBasicBO;
import com.notarios.model.Project;
import com.notarios.model.Transaction;

/**
 * Activity that shows the list of transactions for a given project and enables the user to create or edit one.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public class ActivityProject extends ListActivity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Retrieves the serialized project
		final Project p = (Project) getIntent().getExtras().get("proyectoSerializable");
		
		setContentView(R.layout.project_transactions);
		final TextView textView = (TextView) findViewById(R.id.projectName);
		final List<Transaction> transactions = TransactionBasicBO.getTransactionBasicBO().listTransactions(p);
		textView.setText(p.getName());
		((TextView) findViewById(R.id.projectAmount)).setText(String.valueOf(p.getAmount()) + " "
				+ p.getCurrencySymbol());
		
		// Creates a list adapter to show the list of transactions of a given project.
		setListAdapter(new ArrayAdapter<Transaction>(this, R.layout.project_transactions, transactions) {
			@Override
			public View getView(final int position, final View convertView, final ViewGroup parent) {
				View row;
				// We need the layoutinflater to pick up the view from xml
				final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
				if (null == convertView) {
					row = inflater.inflate(R.layout.list_item, null);
				} else {
					row = convertView;
				}
				final TwoLineListItem view = (TwoLineListItem) row;
				view.getText1().setText((getItem(position)).getDescription());
				view.getText2().setText(String.valueOf((getItem(position)).getAmount()));
				return row;
			}

		});

		final Button b = (Button) findViewById(R.id.addTransaction);
		// Add new transaction is clicked, goes to the edit transaction activity
		b.setOnClickListener(new OnClickListener() {

			public void onClick(final View v) {
				final Intent myIntent = new Intent(v.getContext(), AddTransaction.class);
				myIntent.putExtra("proyectoSerializable", p);
				startActivityForResult(myIntent, 0);
			}
		});

		final ListView lv = getListView();

		// A transaction is clicked, sends the transactio and project to the edit transaction activity
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				final Transaction transaction = (Transaction) parent.getItemAtPosition(position);
				final Intent myIntent = new Intent(view.getContext(), AddTransaction.class);
				myIntent.putExtra("transactionSerializable", transaction);
				myIntent.putExtra("proyectoSerializable", p);
				startActivityForResult(myIntent, 0);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.transactions_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		final Intent myIntent = new Intent(this, AddTransaction.class);
		myIntent.putExtra("proyectoSerializable", (Project) getIntent().getExtras().get("proyectoSerializable"));
		startActivityForResult(myIntent, 0);
		return true;
	}

}
