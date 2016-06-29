package org.appdirect.challenge.utils;

import java.io.IOException;

import org.appdirect.challenge.service.exception.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static final ObjectMapper reader = new ObjectMapper();
	
	public static Object deserializeJson(final String json, Class clazz) {
		try {
			return reader.readValue(json, clazz);
		} catch (IOException e) {
			throw new JsonProcessingException("Error while parsing json", e);
		}
	}

}
