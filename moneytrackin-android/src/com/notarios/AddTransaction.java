/*
 * AddTransaction.java March 2011
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

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.notarios.bo.impl.TransactionBasicBO;
import com.notarios.model.Project;
import com.notarios.model.Transaction;

/**
 * Activity where transactions are edited/created.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public class AddTransaction extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_transaction);

		// Reads the projects serialized to the activity.
		final Project p = (Project) getIntent().getExtras().get("proyectoSerializable");
		// Reads the transaction serialized to the activity, it may be null.
		final Transaction t = (Transaction) getIntent().getExtras().get("transactionSerializable");

		// If it's a transaction edition initializes the view
		if (t != null) {
			((EditText) findViewById(R.id.transactionDescription)).setText(t.getDescription());
			((EditText) findViewById(R.id.transactionAmount)).setText(String.valueOf(t.getAmount()));
			final DatePicker picker = ((DatePicker) findViewById(R.id.transactionDate));
			picker.init(t.getDate().get(Calendar.YEAR), t.getDate().get(Calendar.MONTH),
					t.getDate().get(Calendar.DAY_OF_MONTH), null);
			((EditText) findViewById(R.id.transactionTags)).setText(t.getTagsString());
		}

		final Button b = (Button) findViewById(R.id.saveTransaction);
		// If save button is pressed, save and return
		b.setOnClickListener(new OnClickListener() {

			public void onClick(final View v) {
				Transaction tLocal = new Transaction();
				if (t != null) {
					tLocal = t;
				}
				tLocal.setDescription(((EditText) findViewById(R.id.transactionDescription)).getText().toString());
				String tags = ((EditText) findViewById(R.id.transactionTags)).getText().toString();
				for (String tag : tags.split(" ")) {
					tLocal.getTags().add(tag);
				}
				tLocal.setAmount(Double.parseDouble(((EditText) findViewById(R.id.transactionAmount)).getText()
						.toString()));
				final DatePicker picker = ((DatePicker) findViewById(R.id.transactionDate));
				final Calendar date = Calendar.getInstance();
				date.set(Calendar.YEAR, picker.getYear());
				date.set(Calendar.MONTH, picker.getMonth());
				date.set(Calendar.DAY_OF_MONTH, picker.getDayOfMonth());
				tLocal.setDate(date);
				TransactionBasicBO.getTransactionBasicBO().saveTransaction(tLocal, p);
				finish();
			}
		});
	}

}
