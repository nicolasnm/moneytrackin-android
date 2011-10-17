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

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.notarios.bo.impl.TransactionBasicBO;
import com.notarios.listadapters.MoneytrackinTransactionsListAdapter;
import com.notarios.model.Project;
import com.notarios.model.Transaction;

/**
 * Activity that shows the list of transactions for a given project and enables the user to create or edit one.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public class TransactionsActivity extends AbstractAsyncListActivity {
	
	@Override
    public void onStart() {
        super.onStart();
        new FechTransactionsTask().execute();
    }

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Retrieves the serialized project
		final Project p = (Project) getIntent().getExtras().get("proyectoSerializable");
		
		setContentView(R.layout.project_transactions);

		final Button b = (Button) findViewById(R.id.addTransaction);
		// Add new transaction is clicked, goes to the edit transaction activity
		b.setOnClickListener(new OnClickListener() {
			public void onClick(final View v) {
				final Intent myIntent = new Intent(v.getContext(), AddTransaction.class);
				myIntent.putExtra("proyectoSerializable", p);
				startActivityForResult(myIntent, 0);
			}
		});

	}

	
    private void showResult(List<Transaction> transactions) {
    	MoneytrackinTransactionsListAdapter adapter = new MoneytrackinTransactionsListAdapter(this, transactions);
        setListAdapter(adapter);
        final ListView lv = getListView();
     // A transaction is clicked, sends the transaction and project to the edit transaction activity
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				final Transaction transaction = (Transaction) parent.getItemAtPosition(position);
				final Intent myIntent = new Intent(view.getContext(), AddTransaction.class);
				myIntent.putExtra("transactionSerializable", transaction);
				final Project p = (Project) getIntent().getExtras().get("proyectoSerializable");
				myIntent.putExtra("proyectoSerializable", p);
				startActivityForResult(myIntent, 0);
			}
		});
    }

	// ***************************************
    // Private classes
    // ***************************************
    private class FechTransactionsTask extends AsyncTask<Void, Void, List<Transaction>> {

        @Override
        protected void onPreExecute() {
            // before the network request begins, show a progress indicator
            showProgressDialog("Fetching transactions...");
        }

        @Override
        protected List<Transaction> doInBackground(Void... params) {
            try {
            	final Project p = (Project) getIntent().getExtras().get("proyectoSerializable");
            	return TransactionBasicBO.getTransactionBasicBO().listTransactions(p);
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Transaction> transactions) {
            // after the network request completes, hide the progress indicator
            dismissProgressDialog();
            showResult(transactions);
        }
    }
}
