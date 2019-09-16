package com.suntaragali.restapi;

import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.*;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class JsonPathExample {
	
	/**
	 * Extract details as String and without using Json Path
	 */
	
	@Test
	public void testJsonPath1() {
		String responseAsString = 
				when().
					get("http://jsonplaceholder.typicode.com/photos").
				then().
					extract().asString();
		List<Integer> albumIds = from(responseAsString).get("id");
	}
	
	/**
	 * Extract details as String & fetch using JsonPath
	 */
	@Test
	public void testJsonPath() {
		String json = 
				when().
					get("http://jsonplaceholder.typicode.com/photos").
				then().
				extract().asString();
		JsonPath jsonPath = new JsonPath(json);
		List<String> list = jsonPath.get("title");
		System.out.println(list.size());
	}

}
