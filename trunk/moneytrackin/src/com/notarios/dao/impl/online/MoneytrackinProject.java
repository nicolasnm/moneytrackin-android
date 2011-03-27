/*
 * MoneytrackinProject.java March 2011
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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Class to hold the Moneytrackin answer to a project.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
@Root(name = "project", strict = false)
public class MoneytrackinProject {
	@Attribute(required = false)
	private String id;
	@Element(required = false)
	private String name;
	@Element(required = false)
	private double balance;
	@Element(required = false)
	private char htmlchar;
	@Element(required = false)
	private String currency;

	/**
	 * Gets the id
	 * 
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public final void setId(final String id) {
		this.id = id;
	}

	/**
	 * Gets the name
	 * 
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the name to set
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the balance
	 * 
	 * @return the balance
	 */
	public final double getBalance() {
		return balance;
	}

	/**
	 * Sets the balance.
	 * 
	 * @param balance the balance to set
	 */
	public final void setBalance(final double balance) {
		this.balance = balance;
	}

	/**
	 * Gets the htmlchar
	 * 
	 * @return the htmlchar
	 */
	public final char getHtmlchar() {
		return htmlchar;
	}

	/**
	 * Sets the htmlchar.
	 * 
	 * @param htmlchar the htmlchar to set
	 */
	public final void setHtmlchar(final char htmlchar) {
		this.htmlchar = htmlchar;
	}

	/**
	 * Gets the currency
	 * 
	 * @return the currency
	 */
	public final String getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency.
	 * 
	 * @param currency the currency to set
	 */
	public final void setCurrency(final String currency) {
		this.currency = currency;
	}

}
