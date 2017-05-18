package org.zwakele.htmlparser.egis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

public class ParserMainTestCase {

	ParserMain parserMain; 
	
	@Before
	public void setUp() throws Exception {
		parserMain = new ParserMain();
	}

	@Test
	public void testIsUrlValid() {
		String invalidUrl = "invaliUrl";
		String validUrl = "http://yahoo.com";
		assertFalse(ParserMain.isUrlValid(invalidUrl));
		assertTrue(ParserMain.isUrlValid(validUrl));
	}

	@Test
	public void testJsonValid() throws Exception {
		Map<String, Object> testMap = testMap();
		Method method = parserMain.getClass().getDeclaredMethod("convertMapToJsonAndWriteToConsole", Map.class);
		method.setAccessible(true);
		Object result = method.invoke(parserMain, testMap);
		String jsonString = (String)result;
		assertTrue(isValidJSON(jsonString));
		assertFalse(isValidJSON(invalidJsonString()));
	}
	
	
	private Map<String, Object> testMap() {
		List<String> testStrings = new ArrayList<>();
		Map<String, Object> testMap = new ConcurrentHashMap<>();
		
		testStrings.add("string1");
		testStrings.add("string2");
		testStrings.add("string3");
		
		testMap.put("key1", testStrings);
		testMap.put("key2", testStrings);
		testMap.put("key3", testStrings);
		return testMap;
	}
	
	public boolean isValidJSON(final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getJsonFactory().createJsonParser(json);
			while (parser.nextToken() != null) {
			}
			valid = true;
		} catch (JsonParseException jpe) {
			
		} catch (IOException ioe) {
			
		}
		return valid;
	}
	
	private String invalidJsonString() {
		return "{\"key1\" : [ \"string1\", \"string2\" \"string3\" ], " +
				" \"key2\" : [ \"string1\", \"string2\", \"string3\" ], " +
				" \"key3\" : [ \"string1\", \"string2\", \"string3\" ]}";
	}
	
}
