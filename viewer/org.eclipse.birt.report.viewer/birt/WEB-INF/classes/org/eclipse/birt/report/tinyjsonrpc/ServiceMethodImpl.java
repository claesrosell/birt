package org.eclipse.birt.report.tinyjsonrpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ServiceMethodImpl implements ServiceMethod, Parameterable, Typeable {
	private ServiceContainer serviceContainer;

	private String name;
	private List<String> parameterNames = new ArrayList<String>();
	private List<Type> types = new ArrayList<Type>();

	private String javaMethod;

	private final List<ServiceParameter> serviceParameters = new ArrayList<>();

	private boolean initialized = false;
	private Method method = null;

	/**
	 * @param serviceContainer
	 * @param serviceMethodName
	 */
	public ServiceMethodImpl(ServiceContainer serviceContainer, String serviceMethodName) {
		this.serviceContainer = serviceContainer;
		this.name = serviceMethodName;
	}

	private void initialize() {
		if (this.initialized) {
			return;
		}

		this.initialized = true;

		// Sanity check, parameter names are optional, but types are not!
		if (this.parameterNames.size() > this.types.size()) {
			throw new RuntimeException();
		}

		// Create service parameters
		for (int typeIndex = 0; typeIndex < this.types.size(); typeIndex++) {
			Type type = this.types.get(typeIndex);
			String argName = "arg" + (typeIndex); //$NON-NLS-1$
			if (typeIndex < this.parameterNames.size()) {
				argName = this.parameterNames.get(typeIndex);
			}

			// Create type name string for the service parameter
			String metodArgType = null;
			if (type instanceof Class<?> clazz) {
				metodArgType = clazz.getSimpleName();
			} else {
				metodArgType = type.toString();
			}

			ServiceParameter serviceParameter = new ServiceParameter(argName, metodArgType);
			this.getParameters().add(serviceParameter);
		}

		Method[] methods = serviceContainer.getServiceClass().getMethods();

		for (Method method : methods) {
			if (Objects.equals(method.getName(), getJavaMethod())) {
				Type[] methodParameterTypes = method.getGenericParameterTypes();
				// The name is correct, lets see if the number of arguments are correct
				int argumentListLength = methodParameterTypes.length;
				if (argumentListLength == this.serviceParameters.size()) {
					boolean argsMatch = true;
					// Number of argument: OK!. Lets check the arguments as well!
					for (int index = 0; index < methodParameterTypes.length; index++) {

						Type type = methodParameterTypes[index];

						// Create type name string for the service parameter
						String metodArgType = null;
						if (type instanceof Class<?> clazz) {
							metodArgType = clazz.getSimpleName();
						} else {
							metodArgType = type.toString();
						}

						ServiceParameter serviceParameter = this.serviceParameters.get(index);
						if (!Objects.equals(serviceParameter.getType(), metodArgType)) {
							argsMatch = false;
							break;
						}
					}

					if (argsMatch) {
						// Pick this java method since everything matches
						this.method = method;
						break;
					}
					// Lets check the next method and see if that works.
				}
			}
		}

		if (this.method == null) {
			throw new RuntimeException();
		}
	}

	public Object invoke(ArrayNode parametersArray)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (!this.initialized) {
			initialize();
		}

		ObjectMapper objectMapper = ObjectMapperAccessor.INSTANCE.getObjectMapper();

		Type[] genericParameterTypes = this.method.getGenericParameterTypes();
		Object[] arguments = new Object[genericParameterTypes.length];

		for (int argumentIndex = 0; argumentIndex < genericParameterTypes.length; argumentIndex++) {
			JsonNode parameterJsonValue = parametersArray.get(argumentIndex);
			Type type = genericParameterTypes[argumentIndex];
			JavaType constructedType = objectMapper.constructType(type);

			Object argument = null;
			if (parameterJsonValue instanceof ObjectNode objectNode
					&& constructedType.isTypeOrSubTypeOf(String.class)) {
				argument = objectNode.toString();
			} else {
				argument = objectMapper.convertValue(parameterJsonValue, constructedType);
			}
			arguments[argumentIndex] = argument;
		}

		return this.method.invoke(serviceContainer.getServiceImplementation(), arguments);
	}


	public Object invoke(ObjectNode parametersObject)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (!this.initialized) {
			initialize();
		}

		ObjectMapper objectMapper = ObjectMapperAccessor.INSTANCE.getObjectMapper();

		/* Pick the first method with matching parameter types */
		Type[] genericParameterTypes = this.method.getGenericParameterTypes();
		Object[] arguments = new Object[genericParameterTypes.length];

		int parameterIndex = 0;

		for (int argumentIndex = 0; argumentIndex < genericParameterTypes.length; argumentIndex++) {
			ServiceParameter serviceParameter = this.getParameters().get(parameterIndex);
			String parameterName = serviceParameter.getName();
			JsonNode parameterJsonValue = parametersObject.get(parameterName);
			if (parameterJsonValue != null) {
				Type type = genericParameterTypes[argumentIndex];
				JavaType constructedType = objectMapper.constructType(type);
				Object argument = objectMapper.convertValue(parameterJsonValue, constructedType);

				arguments[argumentIndex] = argument;
			}

			parameterIndex++;
		}

		return this.method.invoke(serviceContainer.getServiceImplementation(), arguments);
	}

	public List<ServiceParameter> getParameters() {
		initialize();
		return this.serviceParameters;
	}

	private String getJavaMethod() {
		return this.javaMethod;
	}

	public Method getImplementationMethod() {
		initialize();
		return this.method;
	}

	@Override
	public Parameterable withJavaMethod(String javaMethod) {
		this.javaMethod = javaMethod;
		return this;
	}

	public Typeable withParameters(String... parameters) {
		this.parameterNames.clear();
		Collections.addAll(this.parameterNames, parameters);
		return this;
	}

	@Override
	public String toString() {
		return String.format("%s [%s]", this.name != null ? this.name : "", this.javaMethod != null //$NON-NLS-1$ //$NON-NLS-2$
				? this.javaMethod
				: ""); //$NON-NLS-1$
	}

	@Override
	public Parameterable withTypes(Type... classes) {
		this.types.clear();
		Collections.addAll(this.types, classes);
		return this;
	}

	public static class ServiceParameter {
		private String name;
		private String type;

		public ServiceParameter(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return this.name;
		}

		public String getType() {
			return this.type;
		}
	}

}
