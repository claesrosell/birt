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
 * JoinList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class JoinList implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private org.eclipse.birt.report.soapengine.api.JoinDefinition[] join;

	public JoinList() {
	}

	public JoinList(org.eclipse.birt.report.soapengine.api.JoinDefinition[] join) {
		this.join = join;
	}

	/**
	 * Gets the join value for this JoinList.
	 *
	 * @return join
	 */
	public org.eclipse.birt.report.soapengine.api.JoinDefinition[] getJoin() {
		return join;
	}

	/**
	 * Sets the join value for this JoinList.
	 *
	 * @param join
	 */
	public void setJoin(org.eclipse.birt.report.soapengine.api.JoinDefinition[] join) {
		this.join = join;
	}

	public org.eclipse.birt.report.soapengine.api.JoinDefinition getJoin(int i) {
		return this.join[i];
	}

	public void setJoin(int i, org.eclipse.birt.report.soapengine.api.JoinDefinition _value) {
		this.join[i] = _value;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof JoinList)) {
			return false;
		}
		JoinList other = (JoinList) obj;
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
		_equals = true && ((this.join == null && other.getJoin() == null)
				|| (this.join != null && java.util.Arrays.equals(this.join, other.getJoin())));
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
		if (getJoin() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getJoin()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getJoin(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
