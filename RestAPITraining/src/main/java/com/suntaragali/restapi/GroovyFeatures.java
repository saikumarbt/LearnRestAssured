package com.suntaragali.restapi;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.*;
import static org.hamcrest.Matchers.greaterThan;
import java.util.List;
import groovy.*;

import org.testng.annotations.Test;

public class GroovyFeatures {
	
	/**
	 * Example for implementing length() & sum() for response content
	 */
	@Test
	public void testLengthOfResponse() {
		when().
			get("http://reqres.in/api/users").
		then().
			body("data.first_name*.length().sum()", greaterThan(10));
			
	}
	
	/**
	 * Extract all attributes as List
	 */
	 
	@Test
	public void testGetResponseAsList() {
		String response = get("http://reqres.in/api/users").asString();
		List<String> ls = from(response).getList("data.email");
		System.out.println("List Size : " + ls.size());
		for(String email : ls) {
			if(email.equals("emma.wong@reqres.in")) {
				System.out.println("Found my email");
			}
		}
	}
	

}
