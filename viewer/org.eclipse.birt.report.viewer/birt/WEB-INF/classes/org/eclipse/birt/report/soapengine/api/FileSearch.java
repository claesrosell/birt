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
 * FileSearch.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class FileSearch implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.String fileType;

	public FileSearch() {
	}

	public FileSearch(java.lang.String fileType) {
		this.fileType = fileType;
	}

	/**
	 * Gets the fileType value for this FileSearch.
	 *
	 * @return fileType
	 */
	public java.lang.String getFileType() {
		return fileType;
	}

	/**
	 * Sets the fileType value for this FileSearch.
	 *
	 * @param fileType
	 */
	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof FileSearch)) {
			return false;
		}
		FileSearch other = (FileSearch) obj;
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
		_equals = true && ((this.fileType == null && other.getFileType() == null)
				|| (this.fileType != null && this.fileType.equals(other.getFileType())));
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
		if (getFileType() != null) {
			_hashCode += getFileType().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
