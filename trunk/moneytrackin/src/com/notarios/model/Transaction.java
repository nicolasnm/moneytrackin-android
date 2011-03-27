/*
 * Transaction.java March 2011
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
package com.notarios.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Holds the transaction information.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id = -1;
	private String description;
	private double amount;
	private Calendar date;
	private List<String> tags;

	/**
	 * getId Gets the id of the transaction.
	 * 
	 * @return The transaction id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * setId Sets the transaction id.
	 * 
	 * @param id
	 *            The transaction id
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * getDescription Gets the transaction description.
	 * 
	 * @return The transaction description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * setDescription Sets the transaction description
	 * 
	 * @param description
	 *            The transaction description
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getAmount Gets the transaction amount.
	 * 
	 * @return The transaction amount
	 */
	public final double getAmount() {
		return amount;
	}

	/**
	 * setAmount Sets the transaction amount.
	 * 
	 * @param amount
	 *            The transaction amount
	 */
	public final void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * getDate Gets the transaction date.
	 * 
	 * @return amount The transaction date
	 */
	public final Calendar getDate() {
		return date;
	}

	/**
	 * setDate Sets the transaction date.
	 * 
	 * @return date The transaction date
	 */
	public final void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * getTags Gets the list of tags
	 * 
	 * @return The list of tags
	 */
	public final List<String> getTags() {
		return tags;
	}

	/**
	 * setTags Sets the list of tags
	 * 
	 * @param tags
	 *            The list of tags
	 */
	public final void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass().equals(obj.getClass())) {
			return this.getId() == ((Transaction) obj).getId();
		}
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		return this.getId();
	}
}
