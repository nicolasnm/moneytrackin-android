/*
 * TransactionBO.java March 2011
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
package com.notarios.bo;

import java.util.List;

import com.notarios.model.Project;
import com.notarios.model.Transaction;

/**
 * The transactions Business Object
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public interface TransactionBO {
	/**
	 * listTransactions List the transactions for a given project.
	 * @param p The project
	 * @return The ist the transactions for a given project.
	 */
	public List<Transaction> listTransactions(Project p);
	
	/**
	 * saveTransaction Saves the transaction related to the given project.
	 * @param t The transaction to save.
	 * @param p The project to save.
	 */
	public void saveTransaction (Transaction t, Project p);
}
