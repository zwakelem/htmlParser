package org.zwakele.htmlparser.egis;

import static org.junit.Assert.*;

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
		assertFalse(parserMain.isUrlValid(invalidUrl));
		assertTrue(parserMain.isUrlValid(validUrl));
	}

}
