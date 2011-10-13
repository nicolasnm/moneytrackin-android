/*
 * ListProjectsResult.java March 2011
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
package com.notarios.dao.impl.online;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.notarios.model.Transaction;

/**
 * Represents the class where to map the Moneytrackin answer to a list projects call.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
@Root(name="result")
public class ListTransactionsResult {
	@Attribute
	private String code;
	@ElementList(inline=true)
	private List<MoneytrackinTransaction> transactions;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the projects
	 */
	public List<MoneytrackinTransaction> getTransactions() {
		return transactions;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setTransactions(List<MoneytrackinTransaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
