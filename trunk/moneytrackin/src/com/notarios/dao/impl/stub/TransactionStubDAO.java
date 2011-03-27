/*
 * TransactionStubDAO.java March 2011
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
package com.notarios.dao.impl.stub;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.notarios.dao.TransactionDAO;
import com.notarios.model.Project;
import com.notarios.model.Transaction;

/**
 * A stub implementation for the TranscationDAO interface. When it returns a list of transactions
 * it ignores the project.
 * 
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell Nicolás Notario McDonnell
 */
public class TransactionStubDAO implements TransactionDAO {

	private static final int NUM_TRANSACTIONS = 10;
	private static final int NUM_TAGS = 2;

	private final List<Transaction> transactions;

	/**
	 * TransactionStubDAO constructor
	 */
	public TransactionStubDAO() {
		transactions = new ArrayList<Transaction>();
		for (int i = 0; i < NUM_TRANSACTIONS; i++) {
			final Transaction t = new Transaction();
			t.setId(i);
			t.setAmount(332.34 * i + 12.34);
			t.setDescription("Compra rober " + i);
			t.setDate(Calendar.getInstance());
			final List<String> tags = new ArrayList<String>();
			for (int j = 0; j < NUM_TAGS; j++) {
				tags.add("Tag" + i + "_" + j);
			}
			t.setTags(tags);
			transactions.add(t);
		}
	}

	/**
	 * @see com.notarios.dao.TransactionDAO#listTransactions(com.notarios.model.Project)
	 */
	public List<Transaction> listTransactions(final Project p) {
		return transactions;
	}

	/**
	 * @see com.notarios.dao.TransactionDAO#addTransaction(com.notarios.model.Transaction, com.notarios.model.Project)
	 */
	public void addTransaction(final Transaction t, final Project p) {
		transactions.add(t);
	}

	/**
	 * @see com.notarios.dao.TransactionDAO#saveTransaction(com.notarios.model.Transaction, com.notarios.model.Project)
	 */
	public void saveTransaction(final Transaction t, final Project p) {
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).equals(t)) {
				transactions.set(i, t);
			}
		}
	}

}
