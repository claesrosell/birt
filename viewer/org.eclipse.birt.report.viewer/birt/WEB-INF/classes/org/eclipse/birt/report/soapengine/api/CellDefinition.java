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
 * CellDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.api;

public class CellDefinition implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int level;
	private boolean isHeader;
	private int rowIndex;
	private int cellIndex;
	private org.eclipse.birt.report.soapengine.api.Font font;
	private org.eclipse.birt.report.soapengine.api.Format format;
	private org.eclipse.birt.report.soapengine.api.ColumnProperties properties;
	private org.eclipse.birt.report.soapengine.api.Alignment alignment;

	public CellDefinition() {
	}

	public CellDefinition(int level, boolean isHeader, int rowIndex, int cellIndex,
			org.eclipse.birt.report.soapengine.api.Font font, org.eclipse.birt.report.soapengine.api.Format format,
			org.eclipse.birt.report.soapengine.api.ColumnProperties properties,
			org.eclipse.birt.report.soapengine.api.Alignment alignment) {
		this.level = level;
		this.isHeader = isHeader;
		this.rowIndex = rowIndex;
		this.cellIndex = cellIndex;
		this.font = font;
		this.format = format;
		this.properties = properties;
		this.alignment = alignment;
	}

	/**
	 * Gets the level value for this CellDefinition.
	 *
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level value for this CellDefinition.
	 *
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Gets the isHeader value for this CellDefinition.
	 *
	 * @return isHeader
	 */
	public boolean isIsHeader() {
		return isHeader;
	}

	/**
	 * Sets the isHeader value for this CellDefinition.
	 *
	 * @param isHeader
	 */
	public void setIsHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

	/**
	 * Gets the rowIndex value for this CellDefinition.
	 *
	 * @return rowIndex
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * Sets the rowIndex value for this CellDefinition.
	 *
	 * @param rowIndex
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * Gets the cellIndex value for this CellDefinition.
	 *
	 * @return cellIndex
	 */
	public int getCellIndex() {
		return cellIndex;
	}

	/**
	 * Sets the cellIndex value for this CellDefinition.
	 *
	 * @param cellIndex
	 */
	public void setCellIndex(int cellIndex) {
		this.cellIndex = cellIndex;
	}

	/**
	 * Gets the font value for this CellDefinition.
	 *
	 * @return font
	 */
	public org.eclipse.birt.report.soapengine.api.Font getFont() {
		return font;
	}

	/**
	 * Sets the font value for this CellDefinition.
	 *
	 * @param font
	 */
	public void setFont(org.eclipse.birt.report.soapengine.api.Font font) {
		this.font = font;
	}

	/**
	 * Gets the format value for this CellDefinition.
	 *
	 * @return format
	 */
	public org.eclipse.birt.report.soapengine.api.Format getFormat() {
		return format;
	}

	/**
	 * Sets the format value for this CellDefinition.
	 *
	 * @param format
	 */
	public void setFormat(org.eclipse.birt.report.soapengine.api.Format format) {
		this.format = format;
	}

	/**
	 * Gets the properties value for this CellDefinition.
	 *
	 * @return properties
	 */
	public org.eclipse.birt.report.soapengine.api.ColumnProperties getProperties() {
		return properties;
	}

	/**
	 * Sets the properties value for this CellDefinition.
	 *
	 * @param properties
	 */
	public void setProperties(org.eclipse.birt.report.soapengine.api.ColumnProperties properties) {
		this.properties = properties;
	}

	/**
	 * Gets the alignment value for this CellDefinition.
	 *
	 * @return alignment
	 */
	public org.eclipse.birt.report.soapengine.api.Alignment getAlignment() {
		return alignment;
	}

	/**
	 * Sets the alignment value for this CellDefinition.
	 *
	 * @param alignment
	 */
	public void setAlignment(org.eclipse.birt.report.soapengine.api.Alignment alignment) {
		this.alignment = alignment;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof CellDefinition)) {
			return false;
		}
		CellDefinition other = (CellDefinition) obj;
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
		_equals = true && this.level == other.getLevel() && this.isHeader == other.isIsHeader()
				&& this.rowIndex == other.getRowIndex() && this.cellIndex == other.getCellIndex()
				&& ((this.font == null && other.getFont() == null)
						|| (this.font != null && this.font.equals(other.getFont())))
				&& ((this.format == null && other.getFormat() == null)
						|| (this.format != null && this.format.equals(other.getFormat())))
				&& ((this.properties == null && other.getProperties() == null)
						|| (this.properties != null && this.properties.equals(other.getProperties())))
				&& ((this.alignment == null && other.getAlignment() == null)
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
		_hashCode += getLevel();
		_hashCode += (isIsHeader() ? Boolean.TRUE : Boolean.FALSE).hashCode();
		_hashCode += getRowIndex();
		_hashCode += getCellIndex();
		if (getFont() != null) {
			_hashCode += getFont().hashCode();
		}
		if (getFormat() != null) {
			_hashCode += getFormat().hashCode();
		}
		if (getProperties() != null) {
			_hashCode += getProperties().hashCode();
		}
		if (getAlignment() != null) {
			_hashCode += getAlignment().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}
