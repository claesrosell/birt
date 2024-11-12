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
 * CategoryChoiceList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class CategoryChoiceList implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private org.eclipse.birt.report.soapengine.api.CategoryChoice[] categoryChoice;

	public CategoryChoiceList() {
	}

	public CategoryChoiceList(org.eclipse.birt.report.soapengine.api.CategoryChoice[] categoryChoice) {
		this.categoryChoice = categoryChoice;
	}

	/**
	 * Gets the categoryChoice value for this CategoryChoiceList.
	 *
	 * @return categoryChoice
	 */
	public org.eclipse.birt.report.soapengine.api.CategoryChoice[] getCategoryChoice() {
		return categoryChoice;
	}

	/**
	 * Sets the categoryChoice value for this CategoryChoiceList.
	 *
	 * @param categoryChoice
	 */
	public void setCategoryChoice(org.eclipse.birt.report.soapengine.api.CategoryChoice[] categoryChoice) {
		this.categoryChoice = categoryChoice;
	}

	public org.eclipse.birt.report.soapengine.api.CategoryChoice getCategoryChoice(int i) {
		return this.categoryChoice[i];
	}

	public void setCategoryChoice(int i, org.eclipse.birt.report.soapengine.api.CategoryChoice _value) {
		this.categoryChoice[i] = _value;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof CategoryChoiceList)) {
			return false;
		}
		CategoryChoiceList other = (CategoryChoiceList) obj;
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
				&& ((this.categoryChoice == null && other.getCategoryChoice() == null) || (this.categoryChoice != null
						&& java.util.Arrays.equals(this.categoryChoice, other.getCategoryChoice())));
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
		if (getCategoryChoice() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getCategoryChoice()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getCategoryChoice(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
