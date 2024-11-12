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
 * ExportCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class ExportCriteria implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.String columnName;
	private java.lang.String operator;
	private java.lang.String value;

	public ExportCriteria() {
	}

	public ExportCriteria(java.lang.String columnName, java.lang.String operator, java.lang.String value) {
		this.columnName = columnName;
		this.operator = operator;
		this.value = value;
	}

	/**
	 * Gets the columnName value for this ExportCriteria.
	 *
	 * @return columnName
	 */
	public java.lang.String getColumnName() {
		return columnName;
	}

	/**
	 * Sets the columnName value for this ExportCriteria.
	 *
	 * @param columnName
	 */
	public void setColumnName(java.lang.String columnName) {
		this.columnName = columnName;
	}

	/**
	 * Gets the operator value for this ExportCriteria.
	 *
	 * @return operator
	 */
	public java.lang.String getOperator() {
		return operator;
	}

	/**
	 * Sets the operator value for this ExportCriteria.
	 *
	 * @param operator
	 */
	public void setOperator(java.lang.String operator) {
		this.operator = operator;
	}

	/**
	 * Gets the value value for this ExportCriteria.
	 *
	 * @return value
	 */
	public java.lang.String getValue() {
		return value;
	}

	/**
	 * Sets the value value for this ExportCriteria.
	 *
	 * @param value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ExportCriteria)) {
			return false;
		}
		ExportCriteria other = (ExportCriteria) obj;
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
				&& ((this.columnName == null && other.getColumnName() == null)
						|| (this.columnName != null && this.columnName.equals(other.getColumnName())))
				&& ((this.operator == null && other.getOperator() == null)
						|| (this.operator != null && this.operator.equals(other.getOperator())))
				&& ((this.value == null && other.getValue() == null)
						|| (this.value != null && this.value.equals(other.getValue())));
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
		if (getColumnName() != null) {
			_hashCode += getColumnName().hashCode();
		}
		if (getOperator() != null) {
			_hashCode += getOperator().hashCode();
		}
		if (getValue() != null) {
			_hashCode += getValue().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
