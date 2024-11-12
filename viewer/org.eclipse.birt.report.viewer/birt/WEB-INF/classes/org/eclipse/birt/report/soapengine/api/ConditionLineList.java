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
 * ConditionLineList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class ConditionLineList implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private org.eclipse.birt.report.soapengine.api.ConditionLine[] conditionLine;

	public ConditionLineList() {
	}

	public ConditionLineList(org.eclipse.birt.report.soapengine.api.ConditionLine[] conditionLine) {
		this.conditionLine = conditionLine;
	}

	/**
	 * Gets the conditionLine value for this ConditionLineList.
	 *
	 * @return conditionLine
	 */
	public org.eclipse.birt.report.soapengine.api.ConditionLine[] getConditionLine() {
		return conditionLine;
	}

	/**
	 * Sets the conditionLine value for this ConditionLineList.
	 *
	 * @param conditionLine
	 */
	public void setConditionLine(org.eclipse.birt.report.soapengine.api.ConditionLine[] conditionLine) {
		this.conditionLine = conditionLine;
	}

	public org.eclipse.birt.report.soapengine.api.ConditionLine getConditionLine(int i) {
		return this.conditionLine[i];
	}

	public void setConditionLine(int i, org.eclipse.birt.report.soapengine.api.ConditionLine _value) {
		this.conditionLine[i] = _value;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ConditionLineList)) {
			return false;
		}
		ConditionLineList other = (ConditionLineList) obj;
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
				&& ((this.conditionLine == null && other.getConditionLine() == null) || (this.conditionLine != null
						&& java.util.Arrays.equals(this.conditionLine, other.getConditionLine())));
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
		if (getConditionLine() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getConditionLine()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getConditionLine(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
