package org.eclipse.birt.report.tinyjsonrpc;

public interface ServiceMethod extends Parameterable, Typeable {
	Parameterable withJavaMethod(String javaMethod);

}
