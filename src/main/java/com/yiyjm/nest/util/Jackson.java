package com.yiyjm.nest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Jonny.Chang
 * @date 2020/05/01
 */
public class Jackson {
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 *
	 */
	private Jackson() {
	}

	/**
	 * get objectmapper
	 *
	 * @return {@link ObjectMapper}
	 */
	public static ObjectMapper getObjectMapper() {
		return mapper;
	}

	/**
	 * @param object
	 * @return {@link String}* @throws JsonProcessingException
	 */
	public static String toJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}

	/**
	 * @param string
	 * @param T
	 * @return {@link T}* @throws IOException
	 */
	public static <T> T toObject(String string, Class<T> T) throws IOException {
		return mapper.readValue(string, T);
	}
}
