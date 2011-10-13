/*
 * TransactionBasicBO.java March 2011
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
package com.notarios.bo.impl;

import java.util.List;

import com.notarios.bo.TransactionBO;
import com.notarios.dao.TransactionDAO;
import com.notarios.model.Project;
import com.notarios.model.Transaction;

/**
 * The transactions Business Object.
 * 
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell Nicolás Notario McDonnell
 */
public final class TransactionBasicBO implements TransactionBO {
	private static TransactionBasicBO singletonTransactionBasicBO = null;
	private final TransactionDAO transactionDAO;

	/**
	 * getTransactionBasicBO Gets the singleton instance of the TransactionBasicBO
	 * 
	 * @return The singleton instance of the TransactionBasicBO
	 */
	public static TransactionBasicBO getTransactionBasicBO() {
		return singletonTransactionBasicBO;
	}

	/**
	 * initTransactionBasicBO Initializes the TransactionBasicBO with a TransactionDAO
	 * 
	 * @param transactionDAO The TransactionDAO to use in the TransactionBO
	 */
	public static void initTransactionBasicBO(final TransactionDAO transactionDAO) {
		singletonTransactionBasicBO = new TransactionBasicBO(transactionDAO);
	}

	/**
	 * TransactionBasicBO constructor
	 * 
	 * @param transactionDAO The TransactionDAO to use in the TransactionBO
	 */
	private TransactionBasicBO(final TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	/**
	 * @see com.notarios.bo.TransactionBO#listTransactions(com.notarios.model.Project)
	 */
	public List<Transaction> listTransactions(final Project p) {
		return transactionDAO.listTransactions(p);
	}

	/**
	 * @see com.notarios.bo.TransactionBO#saveTransaction(com.notarios.model.Transaction, com.notarios.model.Project)
	 */
	public void saveTransaction(final Transaction t, final Project p) {
		if (t.getId() >= 0) {
			transactionDAO.saveTransaction(t, p);
		} else {
			transactionDAO.addTransaction(t, p);
		}
	}

}
