/*
 * TransactionDAO.java March 2011
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
package com.notarios.dao;

import java.util.List;

import com.notarios.model.Project;
import com.notarios.model.Transaction;

/**
 * The transactions DAO.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public interface TransactionDAO {
	
	/**
	 * listTransactions Gets the list of transactions for a given project.
	 * @param p The project
	 * @return The list of transactions.
	 */
	public List<Transaction> listTransactions(Project p);
	
	/**
	 * addTransaction Adds a transaction to the given project.
	 * @param t The transaction to add.
	 * @param p The project where to add the transaction.
	 */
	public void addTransaction (Transaction t, Project p);
	
	/**
	 * saveTransaction Saves the transaction in the given project (it must exist)
	 * @param t The transaction to save
	 * @param p The project where to save the transaction.
	 */
	public void saveTransaction(Transaction t, Project p);
}
