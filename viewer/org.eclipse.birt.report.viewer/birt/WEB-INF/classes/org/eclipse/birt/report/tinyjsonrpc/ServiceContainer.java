package org.eclipse.birt.report.tinyjsonrpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Describes a service that should be available via JSON-RPC
 *
 * @since 3.3
 *
 */
public class ServiceContainer
{
	private Class<?> serviceClass;
	private Object serviceImplementation;
	private Map<String, List<ServiceMethodImpl>> serviceMethods = new HashMap<>();

	public ServiceContainer( Class<?> clazz, Object serviceImpl )
	{
		if ( clazz != null ) {
			this.serviceClass = clazz;
		}
		else {
			this.serviceClass = serviceImpl.getClass();
		}

		this.serviceImplementation = serviceImpl;
	}

	public Class<?> getServiceClass()
	{
		return this.serviceClass;
	}

	public Object getServiceImplementation()
	{
		return this.serviceImplementation;
	}

	/**
	 * @param string
	 * @param object
	 * @param strings
	 */
	public ServiceMethod addMethod(String serviceMethodName) {
		ServiceMethodImpl method = new ServiceMethodImpl(this, serviceMethodName);

		List<ServiceMethodImpl> listOfMethods = this.serviceMethods.computeIfAbsent(serviceMethodName, service -> {
			return new ArrayList();
		});

		listOfMethods.add(method);
		return method;
	}

	public Object invoke(String methodName, ArrayNode parametersArray)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/*
		 * Begin to look for matching methods
		 */
		List<ServiceMethodImpl> matchingMethods = getMatchingMethods(methodName, parametersArray.size());

		if (matchingMethods.isEmpty()) {
			throw new RuntimeException("No method with matching parameters for method: " + methodName); //$NON-NLS-1$
		}

		ObjectMapper objectMapper = ObjectMapperAccessor.INSTANCE.getObjectMapper();

		/* Pick the first method with matching parameter types */
		for (ServiceMethodImpl method : matchingMethods) {
			Method javaMethod = method.getImplementationMethod();
			Type[] genericParameterTypes = javaMethod.getGenericParameterTypes();
			Object[] arguments = new Object[genericParameterTypes.length];

			int parameterIndex = 0;

			for (int argumentIndex = 0; argumentIndex < genericParameterTypes.length; argumentIndex++) {
				org.eclipse.birt.report.tinyjsonrpc.ServiceMethodImpl.ServiceParameter serviceParameter = method
						.getParameters().get(parameterIndex);
				String parameterName = serviceParameter.getName();
				JsonNode parameterJsonValue = parametersArray.get(parameterIndex);
				Type type = genericParameterTypes[argumentIndex];
				JavaType constructedType = objectMapper.constructType(type);
				Object argument = null;
				if ( parameterJsonValue instanceof ObjectNode objectNode
						&& constructedType.isTypeOrSubTypeOf( String.class ) ) {
					argument = objectNode.toString();
				}
				else {
					argument = objectMapper.convertValue( parameterJsonValue, constructedType );
				}
				arguments[argumentIndex] = argument;

				parameterIndex++;
			}

			return javaMethod.invoke(getServiceImplementation(), arguments);
		}

		throw new RuntimeException();
	}

	protected List<ServiceMethodImpl> getMatchingMethods(String methodName, int nrArgs) {
		/*
		 * Begin to look for matching methods
		 */
		List<ServiceMethodImpl> matchingMethods = new ArrayList<>();
		List<ServiceMethodImpl> methodsForName = this.serviceMethods.get(methodName);

		if (methodsForName != null) {
			/* Disqualify methods with the wrong nr of parameters. */
			for (ServiceMethodImpl serviceMethod : methodsForName) {
				if (nrArgs == serviceMethod.getParameters().size()) {
					matchingMethods.add(serviceMethod);
				}
			}
		}
		return matchingMethods;
	}

}
