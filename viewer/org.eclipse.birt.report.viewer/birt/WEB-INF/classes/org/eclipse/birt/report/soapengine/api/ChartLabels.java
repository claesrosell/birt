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
 * ChartLabels.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class ChartLabels implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.String title;
	private java.lang.String XAxis;
	private java.lang.String[] YAxis;

	public ChartLabels() {
	}

	public ChartLabels(java.lang.String title, java.lang.String XAxis, java.lang.String[] YAxis) {
		this.title = title;
		this.XAxis = XAxis;
		this.YAxis = YAxis;
	}

	/**
	 * Gets the title value for this ChartLabels.
	 *
	 * @return title
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * Sets the title value for this ChartLabels.
	 *
	 * @param title
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * Gets the XAxis value for this ChartLabels.
	 *
	 * @return XAxis
	 */
	public java.lang.String getXAxis() {
		return XAxis;
	}

	/**
	 * Sets the XAxis value for this ChartLabels.
	 *
	 * @param XAxis
	 */
	public void setXAxis(java.lang.String XAxis) {
		this.XAxis = XAxis;
	}

	/**
	 * Gets the YAxis value for this ChartLabels.
	 *
	 * @return YAxis
	 */
	public java.lang.String[] getYAxis() {
		return YAxis;
	}

	/**
	 * Sets the YAxis value for this ChartLabels.
	 *
	 * @param YAxis
	 */
	public void setYAxis(java.lang.String[] YAxis) {
		this.YAxis = YAxis;
	}

	public java.lang.String getYAxis(int i) {
		return this.YAxis[i];
	}

	public void setYAxis(int i, java.lang.String _value) {
		this.YAxis[i] = _value;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ChartLabels)) {
			return false;
		}
		ChartLabels other = (ChartLabels) obj;
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
				&& ((this.title == null && other.getTitle() == null)
						|| (this.title != null && this.title.equals(other.getTitle())))
				&& ((this.XAxis == null && other.getXAxis() == null)
						|| (this.XAxis != null && this.XAxis.equals(other.getXAxis())))
				&& ((this.YAxis == null && other.getYAxis() == null)
						|| (this.YAxis != null && java.util.Arrays.equals(this.YAxis, other.getYAxis())));
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
		if (getTitle() != null) {
			_hashCode += getTitle().hashCode();
		}
		if (getXAxis() != null) {
			_hashCode += getXAxis().hashCode();
		}
		if (getYAxis() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getYAxis()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getYAxis(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
