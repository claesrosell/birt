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
 * ColumnProperties.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class ColumnProperties implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private double width;
	private org.eclipse.birt.report.soapengine.api.Alignment alignment;

	public ColumnProperties() {
	}

	public ColumnProperties(double width, org.eclipse.birt.report.soapengine.api.Alignment alignment) {
		this.width = width;
		this.alignment = alignment;
	}

	/**
	 * Gets the width value for this ColumnProperties.
	 *
	 * @return width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Sets the width value for this ColumnProperties.
	 *
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Gets the alignment value for this ColumnProperties.
	 *
	 * @return alignment
	 */
	public org.eclipse.birt.report.soapengine.api.Alignment getAlignment() {
		return alignment;
	}

	/**
	 * Sets the alignment value for this ColumnProperties.
	 *
	 * @param alignment
	 */
	public void setAlignment(org.eclipse.birt.report.soapengine.api.Alignment alignment) {
		this.alignment = alignment;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ColumnProperties)) {
			return false;
		}
		ColumnProperties other = (ColumnProperties) obj;
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
		_equals = true && this.width == other.getWidth() && ((this.alignment == null && other.getAlignment() == null)
				|| (this.alignment != null && this.alignment.equals(other.getAlignment())));
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
		_hashCode += new Double(getWidth()).hashCode();
		if (getAlignment() != null) {
			_hashCode += getAlignment().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
