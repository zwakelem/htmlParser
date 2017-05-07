package org.zwakele.htmlparser.egis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.routines.UrlValidator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserMain {
	
	private static Element rootElement;
	private static Map<String, Object> jsonMap = new LinkedHashMap<>();
	

	public static void main(String[] args) {
		
		String urlString = args[0];
		if(isUrlValid(urlString)) {
			initialise(urlString);
			parseHtmlToMap();
			convertMapToJsonAndWriteToConsole(jsonMap);
		} else {
			System.err.println("URL invalid or null");
		}
	}
	
	protected static boolean isUrlValid(String urlString) {
		return new UrlValidator().isValid(urlString);
	}
	
	private static void initialise(String urlString) {
		try {
			Document doc = Jsoup.connect(urlString).get();
			rootElement = doc.select("article").first();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void parseHtmlToMap() {
		if(rootElement != null) {
			Elements headerElements = headersAsElements();
			for(Element header: headerElements) {
				Element tableElement = header.nextElementSibling();
				List<String> techs = getTechnologiesForArea(tableElement);
				jsonMap.put(header.text(), techs);
			}
		} else {
			System.err.println("Could not find expected root element");
		}
	}

	private static void convertMapToJsonAndWriteToConsole(Map<String, Object> jsonMap) {
		if(jsonMap != null && !jsonMap.isEmpty()) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonNode = mapper.convertValue(jsonMap, JsonNode.class);
				System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode));
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("json Map is null or empty");
		}
	}
	
	private static Elements headersAsElements() {
		return rootElement.select("h2");
	}
	
	private static List<String> getTechnologiesForArea(Element table) {
		List<String> technologies = new ArrayList<>();
		Elements elements = table.select("tbody").select("tr");
		for(Element element : elements) {
			Element tdElement = element.select("td").first();
			if(tdElement != null && !tdElement.text().isEmpty()) {
				technologies.add(element.select("td").first().text());
			}
		}
		return technologies;
	}

}
