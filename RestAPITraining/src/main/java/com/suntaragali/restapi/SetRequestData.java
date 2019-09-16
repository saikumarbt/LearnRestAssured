package com.suntaragali.restapi;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class SetRequestData {
	
	/**
	 * Using CONNECT for HTTPS request
	 */
	
	@Test
	public void testConnect() {
		when().
			request("CONNECT", "https://api.fonts.com/rest/json/Accounts/").
		then().
			assertThat().statusCode(400);
	}
	
	/**
	 * Usage of Query Parameter for GET Request
	 */
	@Test
	public void testQueryParams() {
		given().
			//queryParam("page", "2"). // OR
			param("page", "2").
		when().
			get("http://reqres.in/api/users").
		then().
			assertThat().statusCode(200);
	}
	
	/**
	 * Usage of Form Parameter for POST request
	 */
	/*@Test
	public void testFormParams() {
		given().
			param("name", "morpheus").
			param("job", "leader").
		when().
			post("http://reqres.in/api/users").
		then().
			assertThat().statusCode(201).extract();
	}*/
	
	/**
	 * Usage of Path Parameters
	 */
	@Test
	public void testPathParams() {
		given().
			pathParam("type", "json").
			pathParam("section", "Domains").
		when().
			post("http://api.fonts.com/rest/{type}/{section}/").
		then().
			statusCode(400);
	}
	
	/**
	 * Set content type
	 */
	@Test
	public void testSetContentType() {
		given().
			contentType(ContentType.JSON).
			contentType("application/json; charset=utf-8").
		when().
			get("https://api.fonts.com/rest/json/Accounts/").
		then().
			statusCode(400);
	}
}
