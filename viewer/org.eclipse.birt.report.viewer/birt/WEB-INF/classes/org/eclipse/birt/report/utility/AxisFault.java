package org.eclipse.birt.report.utility;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;

/**
 * @since 3.3
 *
 */
public class AxisFault extends RemoteException {

	private static final long serialVersionUID = 1L;

	private String faultCode;
	private String faultString;
	private String faultReason;

	/**
	 *
	 */
	public AxisFault() {
		super();
	}

	/**
	 * @param message
	 */
	public AxisFault(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public AxisFault(String message, Exception e) {
		super(message, e);
	}

	/**
	 * @param qName
	 */
	public void setFaultCode(QName qName) {
		this.faultCode = qName.toString();
	}

	/**
	 * @param faultString
	 */
	public void setFaultString(String faultString) {
		this.faultString = faultString;

	}

	/**
	 * @param faultReason
	 */
	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}

	@Override
	public String getMessage() {
		// ToDo : Build message from, message, code, fault and reason.
		return super.getMessage();
	}

	/**
	 * @param e
	 * @return
	 */
	public static AxisFault makeFault(Exception e) {
		return new AxisFault(e.getMessage(), e);
	}

	/**
	 * @return
	 */
	public String getFaultString() {
		return this.faultString;
	}

	/**
	 * @param exceptionQName
	 * @param stackTrace
	 */
	public void addFaultDetail(QName exceptionQName, String stackTrace) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param stackTrace
	 */
	public void addFaultDetailString(String stackTrace) {
		// TODO Auto-generated method stub

	}

}
