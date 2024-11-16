package org.eclipse.birt.report.tinyjsonrpc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 3.3
 *
 */
public class ObjectMapperAccessor {

	/**
	 * Accessor to ThreadLocal instances of ObjectMapper
	 */
	public static final ObjectMapperAccessor INSTANCE = new ObjectMapperAccessor();

	private final ThreadLocal<ObjectMapper> objectMapper;

	private ObjectMapperAccessor()
	{
		this.objectMapper = ThreadLocal.withInitial(ObjectMapper::new);
	}

	/**
	 * @return A ThreadLocal version of a ObjectMapper;
	 */
	public ObjectMapper getObjectMapper() {
		return this.objectMapper.get();
	}
}
