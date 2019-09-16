package com.suntaragali.restapi;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.IOException;
import java.io.InputStream;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ExtractingResponse {
	/**
	 * To extract all response as a String
	 */
	@Test
	public void testGetResponseAsString() {
		String responseAsString = get("http://reqres.in/api/users/").asString();
		System.out.println("My Response: \n\n\n"+ responseAsString);
	}
	
	/**
	 * To extract all response as Input Stream
	 * @throws IOException
	 */
	@Test
	public void testGetResponseAsInputStream() throws IOException {
		InputStream stream = get("http://reqres.in/api/users").asInputStream();
		System.out.println("Stream Length: "+ stream.toString().length());
		stream.close();
	}
	
	/**
	 * To extract all response as ByteArray
	 */
	@Test
	public void testGetResponseAsByteArray() {
		byte[] byteArray = get("http://reqres.in/api/users").asByteArray();
		System.out.println(byteArray.length);
	}
	
	/**
	 * To extract response details using Path
	 */
	
	@Test
	public void testResponseDetailsUsingPath() {
		String href = when().get("http://jsonplaceholder.typicode.com/photos/1").
					  then().
					  contentType(ContentType.JSON).
					  body("albumId", equalTo(1)).
					  extract().
					  path("url");
		System.out.println(href);
		when().get(href).then().assertThat().statusCode(200);
	}
	
	/**
	 * To extract response details using path in one line
	 */
	@Test
	public void testExtractResponseInOneLine() {
		//type 1:
		String href1 = get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
		System.out.println("Fetched URL 1 : "+href1);
		when().get(href1).then().statusCode(200);
		
		//type2:
		String href2 = get("http://jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath().getString("thumbnailUrl");
		System.out.println("Fetched URL2 : "+href2);
		when().get(href2).then().statusCode(200);
	}
	
	/**
	 * Extract Response content using extract()
	 */
	@Test
	public void testExtractContentUsingReponse() {
		Response response = 
		when().	
			get("http://jsonplaceholder.typicode.com/photos/1").
		then().
		extract().
			response();
		System.out.println("Content Type: "+response.contentType());
		System.out.println("Href : "+response.path("url"));
		System.out.println("Status Code : "+response.statusCode());
	}
	
	/**
	 * Verify response schema for an existing schema
	 */
	@Test
	public void testSchema() {
		given().
			get("http://reqres.in/api/users").
			then().
			assertThat().body(matchesJsonSchemaInClasspath("reqres_usersSchema.json"));
	}
}
