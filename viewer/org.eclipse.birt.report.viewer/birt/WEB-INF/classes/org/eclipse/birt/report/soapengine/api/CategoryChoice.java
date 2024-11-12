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
 * CategoryChoice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class CategoryChoice implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.String category;
	private java.lang.String pattern;

	public CategoryChoice() {
	}

	public CategoryChoice(java.lang.String category, java.lang.String pattern) {
		this.category = category;
		this.pattern = pattern;
	}

	/**
	 * Gets the category value for this CategoryChoice.
	 *
	 * @return category
	 */
	public java.lang.String getCategory() {
		return category;
	}

	/**
	 * Sets the category value for this CategoryChoice.
	 *
	 * @param category
	 */
	public void setCategory(java.lang.String category) {
		this.category = category;
	}

	/**
	 * Gets the pattern value for this CategoryChoice.
	 *
	 * @return pattern
	 */
	public java.lang.String getPattern() {
		return pattern;
	}

	/**
	 * Sets the pattern value for this CategoryChoice.
	 *
	 * @param pattern
	 */
	public void setPattern(java.lang.String pattern) {
		this.pattern = pattern;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof CategoryChoice)) {
			return false;
		}
		CategoryChoice other = (CategoryChoice) obj;
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
				&& ((this.category == null && other.getCategory() == null)
						|| (this.category != null && this.category.equals(other.getCategory())))
				&& ((this.pattern == null && other.getPattern() == null)
						|| (this.pattern != null && this.pattern.equals(other.getPattern())));
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
		if (getCategory() != null) {
			_hashCode += getCategory().hashCode();
		}
		if (getPattern() != null) {
			_hashCode += getPattern().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
