/*******************************************************************************
 * Copyright (c) 2021 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   See git history
 *******************************************************************************/
/**
 * BRDExpression.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class BRDExpression implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.String expression;
	private java.lang.Boolean isValid;
	private java.lang.String parserError;

	public BRDExpression() {
	}

	public BRDExpression(java.lang.String expression, java.lang.Boolean isValid, java.lang.String parserError) {
		this.expression = expression;
		this.isValid = isValid;
		this.parserError = parserError;
	}

	/**
	 * Gets the expression value for this BRDExpression.
	 *
	 * @return expression
	 */
	public java.lang.String getExpression() {
		return expression;
	}

	/**
	 * Sets the expression value for this BRDExpression.
	 *
	 * @param expression
	 */
	public void setExpression(java.lang.String expression) {
		this.expression = expression;
	}

	/**
	 * Gets the isValid value for this BRDExpression.
	 *
	 * @return isValid
	 */
	public java.lang.Boolean getIsValid() {
		return isValid;
	}

	/**
	 * Sets the isValid value for this BRDExpression.
	 *
	 * @param isValid
	 */
	public void setIsValid(java.lang.Boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * Gets the parserError value for this BRDExpression.
	 *
	 * @return parserError
	 */
	public java.lang.String getParserError() {
		return parserError;
	}

	/**
	 * Sets the parserError value for this BRDExpression.
	 *
	 * @param parserError
	 */
	public void setParserError(java.lang.String parserError) {
		this.parserError = parserError;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof BRDExpression)) {
			return false;
		}
		BRDExpression other = (BRDExpression) obj;
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true
				&& ((this.expression == null && other.getExpression() == null)
						|| (this.expression != null && this.expression.equals(other.getExpression())))
				&& ((this.isValid == null && other.getIsValid() == null)
						|| (this.isValid != null && this.isValid.equals(other.getIsValid())))
				&& ((this.parserError == null && other.getParserError() == null)
						|| (this.parserError != null && this.parserError.equals(other.getParserError())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	@Override
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (getExpression() != null) {
			_hashCode += getExpression().hashCode();
		}
		if (getIsValid() != null) {
			_hashCode += getIsValid().hashCode();
		}
		if (getParserError() != null) {
			_hashCode += getParserError().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
