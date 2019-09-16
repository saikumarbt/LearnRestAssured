package com.suntaragali.restapi;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasXPath;

public class RestBasicsXML {
	
	/**
	 * To test xml response for single body content
	 */
	
	@Test
	public void verifySingleContent() {
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
		then().
			body("CUSTOMER.ID", equalTo("10")).
			log().all();
	}
	
	/**
	 * To test XML response for multiple contents
	 */
	@Test
	public void verifyXMLResponse() {
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
		then().
			body("CUSTOMER.ID", equalTo("10")).
			body("CUSTOMER.FIRSTNAME", equalTo("Sue")).
			body("CUSTOMER.LASTNAME", equalTo("Fuller")).
			body("CUSTOMER.STREET", equalTo("135 Upland Pl.")).
			body("CUSTOMER.CITY", equalTo("Dallas"));
	}
	
	/**
	 * Compare complete text in one go
	 */
	@Test
	public void verifyCompleteTextInOneGo() {
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
		then().
			body("CUSTOMER.text()", equalTo("10SueFuller135 Upland Pl.Dallas")).log().all();
	}
	
	/**
	 * Validating XMl response using XPath
	 */
	@Test
	public void verifyResponseUsingXPath() {
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
		then().
			body(hasXPath("/CUSTOMER/FIRSTNAME", containsString("Sue")));
	}
	
	/**
	 * Validating XML response using XPath
	 */
	@Test
	public void verifyResponseUsingXPath2() {
		given().
			get("http://www.thomas-bayer.com/sqlrest/INVOICE/").
		then().
			body(hasXPath("/INVOICEList/INVOICE[text()='40']")).log().all();
	}
}
