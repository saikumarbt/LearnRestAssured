package com.suntaragali.restapi;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;


public class RestBasicsJSON {

	/**
	 * Test to check the status code 
	 */
	@Test
	public void verifyStatusCode() {
		given().get("http://jsonplaceholder.typicode.com/posts/3").then().assertThat().statusCode(200);
	}
	
	/**
	 * Verify code and print complete response in console
	 */
	@Test
	public void testLoggingFeature() {
		given().
			get("http://jsonplaceholder.typicode.com/posts/").
		then().
			assertThat().statusCode(200).
		    log().all();
	}
	
	/**
	 * Verify multiple content from response using org.hamcrest.Matchers Library
	 *
	 */
	@Test
	public void testHasItemMatcherMethod() {
		given().
			get("http://jsonplaceholder.typicode.com/users").
		then().
			assertThat().body("name", hasItems("Ervin Howell", "Patricia Lebsack"));
	}
	
	/**
	 * Example to set Headers & Parameters
	 */
	
	@Test
	public void testParametersAndHeaders() {
		
		given().
			header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com").
			header("X-RapidAPI-Key", "b637bb27ddmsh1fcb0d2fe729508p1f063ajsn2a38c56d6056").
		when().
			get("https://restcountries-v1.p.rapidapi.com/currency/INR").
		then().assertThat().statusCode(200).log().all();
		
	}
	
	/**
	 * Using 'and()' to increase readability
	 * generally used when writing in one line Xpath style
	 */
	
	@Test
	public void testANDFeature() {

		given().header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com").and()
				.header("X-RapidAPI-Key", "b637bb27ddmsh1fcb0d2fe729508p1f063ajsn2a38c56d6056").when()
				.get("https://restcountries-v1.p.rapidapi.com/currency/INR").then().assertThat().statusCode(200).log()
				.all();

	}
}
