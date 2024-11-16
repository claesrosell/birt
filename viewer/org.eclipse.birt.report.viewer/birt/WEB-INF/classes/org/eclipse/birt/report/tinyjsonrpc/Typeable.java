package org.eclipse.birt.report.tinyjsonrpc;

import java.lang.reflect.Type;

/**
 * Interface used in the JSON-RPC service builder
 *
 * @since 3.3
 *
 */
public interface Typeable {
	/**
	 *
	 * @param types The types of the arguments
	 * @return A Parameterable object
	 */
	Parameterable withTypes(Type... types);
}
