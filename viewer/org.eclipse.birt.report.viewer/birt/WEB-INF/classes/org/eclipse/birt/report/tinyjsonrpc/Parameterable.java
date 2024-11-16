package org.eclipse.birt.report.tinyjsonrpc;

/**
 * Interface used in the JSON-RPC service builder
 *
 * @since 3.3
 *
 */
public interface Parameterable {
	/**
	 *
	 * @param strings A list of Strings with parameter names. Used for "named
	 *                parameters"
	 * @return A Typeable object
	 */
	Typeable withParameters(String... strings);
}