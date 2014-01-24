package com.harpy.hag.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

public class JsonUtil {

	public static String objToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, object);
			try {
				return out.toString("utf-8");
			} catch (Exception e) {
				return out.toString();
			}			
		} catch (JsonGenerationException e) {
			return null;
		} catch (JsonMappingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonToObj(String json, Class objClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, objClass);
		} catch (JsonParseException e) {
			return null;
		} catch (JsonMappingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonfileToObj(MultipartFile jsonfile, Class objClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			InputStreamReader isr = new InputStreamReader(jsonfile.getInputStream(), "UTF-8");
			return mapper.readValue(isr, objClass);
		} catch (IOException e1) {
			return null;
		}
	}
}
