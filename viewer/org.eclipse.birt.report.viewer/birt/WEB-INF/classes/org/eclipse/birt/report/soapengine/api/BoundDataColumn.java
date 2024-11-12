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
 * BoundDataColumn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class BoundDataColumn implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.String name;
	private java.lang.String expression;
	private java.lang.String type;

	public BoundDataColumn() {
	}

	public BoundDataColumn(java.lang.String name, java.lang.String expression, java.lang.String type) {
		this.name = name;
		this.expression = expression;
		this.type = type;
	}

	/**
	 * Gets the name value for this BoundDataColumn.
	 *
	 * @return name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * Sets the name value for this BoundDataColumn.
	 *
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets the expression value for this BoundDataColumn.
	 *
	 * @return expression
	 */
	public java.lang.String getExpression() {
		return expression;
	}

	/**
	 * Sets the expression value for this BoundDataColumn.
	 *
	 * @param expression
	 */
	public void setExpression(java.lang.String expression) {
		this.expression = expression;
	}

	/**
	 * Gets the type value for this BoundDataColumn.
	 *
	 * @return type
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * Sets the type value for this BoundDataColumn.
	 *
	 * @param type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof BoundDataColumn)) {
			return false;
		}
		BoundDataColumn other = (BoundDataColumn) obj;
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
				&& ((this.name == null && other.getName() == null)
						|| (this.name != null && this.name.equals(other.getName())))
				&& ((this.expression == null && other.getExpression() == null)
						|| (this.expression != null && this.expression.equals(other.getExpression())))
				&& ((this.type == null && other.getType() == null)
						|| (this.type != null && this.type.equals(other.getType())));
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
		if (getName() != null) {
			_hashCode += getName().hashCode();
		}
		if (getExpression() != null) {
			_hashCode += getExpression().hashCode();
		}
		if (getType() != null) {
			_hashCode += getType().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
