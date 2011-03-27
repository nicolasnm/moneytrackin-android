/*
 * Project.java March 2011
 *
 * Copyright (C) 2011, 
 * 		Nicolás Notario McDonnell <nicolasnm@gmail.com>
 *      Roberto Notario McDonnell <zanaskw@gmail.com> * 
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

/**
 * Holds the project information
 * 
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell Nicolás Notario McDonnell
 */
@SuppressWarnings("serial")
public class Project implements Serializable {
	private String name = null;
	private int id = -1;
	private double amount = 0.0;
	private String currency;
	private char currencySymbol;

	/**
	 * Empty constructor
	 */
	public Project() {

	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName() + ": " + getAmount() + " " + getCurrency();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(final double amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	/**
	 * @return the currencySymbol
	 */
	public char getCurrencySymbol() {
		return currencySymbol;
	}

	/**
	 * @param currencySymbol the currencySymbol to set
	 */
	public void setCurrencySymbol(final char currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
}