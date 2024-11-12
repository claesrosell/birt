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
 * Binding.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class Binding implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long rptElementId;
	private long dataSetId;

	public Binding() {
	}

	public Binding(long rptElementId, long dataSetId) {
		this.rptElementId = rptElementId;
		this.dataSetId = dataSetId;
	}

	/**
	 * Gets the rptElementId value for this Binding.
	 *
	 * @return rptElementId
	 */
	public long getRptElementId() {
		return rptElementId;
	}

	/**
	 * Sets the rptElementId value for this Binding.
	 *
	 * @param rptElementId
	 */
	public void setRptElementId(long rptElementId) {
		this.rptElementId = rptElementId;
	}

	/**
	 * Gets the dataSetId value for this Binding.
	 *
	 * @return dataSetId
	 */
	public long getDataSetId() {
		return dataSetId;
	}

	/**
	 * Sets the dataSetId value for this Binding.
	 *
	 * @param dataSetId
	 */
	public void setDataSetId(long dataSetId) {
		this.dataSetId = dataSetId;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Binding)) {
			return false;
		}
		Binding other = (Binding) obj;
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
		_equals = true && this.rptElementId == other.getRptElementId() && this.dataSetId == other.getDataSetId();
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
		_hashCode += Long.valueOf(getRptElementId()).hashCode();
		_hashCode += Long.valueOf(getDataSetId()).hashCode();
		__hashCodeCalc = false;
		return _hashCode;
	}
}
