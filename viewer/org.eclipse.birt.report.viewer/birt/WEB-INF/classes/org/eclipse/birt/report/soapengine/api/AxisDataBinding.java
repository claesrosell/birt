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
 * AxisDataBinding.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class AxisDataBinding implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private org.eclipse.birt.report.soapengine.api.ColumnDefinition columnData;
	private org.eclipse.birt.report.soapengine.api.SectionDefinition sectionData;

	public AxisDataBinding() {
	}

	public AxisDataBinding(org.eclipse.birt.report.soapengine.api.ColumnDefinition columnData,
			org.eclipse.birt.report.soapengine.api.SectionDefinition sectionData) {
		this.columnData = columnData;
		this.sectionData = sectionData;
	}

	/**
	 * Gets the columnData value for this AxisDataBinding.
	 *
	 * @return columnData
	 */
	public org.eclipse.birt.report.soapengine.api.ColumnDefinition getColumnData() {
		return columnData;
	}

	/**
	 * Sets the columnData value for this AxisDataBinding.
	 *
	 * @param columnData
	 */
	public void setColumnData(org.eclipse.birt.report.soapengine.api.ColumnDefinition columnData) {
		this.columnData = columnData;
	}

	/**
	 * Gets the sectionData value for this AxisDataBinding.
	 *
	 * @return sectionData
	 */
	public org.eclipse.birt.report.soapengine.api.SectionDefinition getSectionData() {
		return sectionData;
	}

	/**
	 * Sets the sectionData value for this AxisDataBinding.
	 *
	 * @param sectionData
	 */
	public void setSectionData(org.eclipse.birt.report.soapengine.api.SectionDefinition sectionData) {
		this.sectionData = sectionData;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof AxisDataBinding)) {
			return false;
		}
		AxisDataBinding other = (AxisDataBinding) obj;
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
				&& ((this.columnData == null && other.getColumnData() == null)
						|| (this.columnData != null && this.columnData.equals(other.getColumnData())))
				&& ((this.sectionData == null && other.getSectionData() == null)
						|| (this.sectionData != null && this.sectionData.equals(other.getSectionData())));
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
		if (getColumnData() != null) {
			_hashCode += getColumnData().hashCode();
		}
		if (getSectionData() != null) {
			_hashCode += getSectionData().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
