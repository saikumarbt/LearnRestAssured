package com.suntaragali.restapi;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.endsWith;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class VerifyResponse {
	
	/**
	 * Verify status code
	 */
	@Test
	public void testResponseStatus() {
		given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().statusCode(200);
		given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().statusLine("HTTP/1.1 200 OK");
		given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().statusLine(containsString("OK"));
	}
	
	/** 
	 * Verify headers
	 */
	@Test
	public void testResponseHeaders() {
		given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().header("X-Powered-By", "Express").log().headers();
		given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().headers("Vary", "Origin, Accept-Encoding", "Content-Type", containsString("application/json"));
	}
	
	/** 
	 * Verify Content Type
	 */
	@Test
	public void testContentType() {
		given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().contentType(ContentType.JSON);
	}
	
	/**
	 * Response body text verification
	 */
	@Test
	public void testBodyInResponse () {
		String responseAsString = get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/02/").asString();
		System.out.println("Response as String : "+responseAsString);
		given().get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/02/").then().assertThat().body(equalTo(responseAsString));
	}
	
	/**
	 * Response verification using Java8 lambda expression
	 */
	@Test
	public void testResponseParameters() {
		
		// Java 7 approach
		given().
			get("http://jsonplaceholder.typicode.com/photos/1").
		then().
			body("thumbnailUrl", new ResponseAwareMatcher<Response>() {
				public Matcher<?> matcher(Response response){
					return equalTo("https://via.placeholder.com/150/92c952");
				}
			});
		
		//Java 8 Lambda expression
		given().get("http://jsonplaceholder.typicode.com/photos/1/").then().body("thumbnailUrl", response -> equalTo("https://via.placeholder.com/150/92c952"));
		given().get("http://jsonplaceholder.typicode.com/photos/1/").then().body("thumbnailUrl", endsWith("92c952"));
	}
	
	
}
