/*
 * RemoteOperationException.java March 2011
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

package com.notarios.exceptions;

/**
 * Class that represents an exception during a remote operation.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public class RemoteOperationException extends Exception {
	private static final long serialVersionUID = -4145316559738873893L;

	/**
	 * RemoteOperationException constructor
	 * @param exceptionCode
	 */
	public RemoteOperationException(final String exceptionCode) {
		super(exceptionCode);
	}

	/**
	 * RemoteOperationException constructor
	 * @param exceptionCode
	 * @param t
	 */
	public RemoteOperationException(final String exceptionCode, final Throwable t) {
		super(exceptionCode, t);
	}
}
