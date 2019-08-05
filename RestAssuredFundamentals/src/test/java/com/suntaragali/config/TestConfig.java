package com.suntaragali.config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.*;

public class TestConfig {
	
	public static RequestSpecification videoGame_requestSpec;
	public static RequestSpecification football_requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void setup() {
		
		//RestAssured.proxy("localhost", 8888);
		
		videoGame_requestSpec = new RequestSpecBuilder().
				setBaseUri("http://localhost").
				setPort(8080).
				setBasePath("/app").
				addHeader("Content-Type", "application/xml").
				addHeader("Accept", "application/xml").build();
		
		football_requestSpec = new RequestSpecBuilder().
				setBaseUri("http://api.football-data.org").
				setBasePath("/v2/").
				addHeader("X-Auth-Token", "b1643884861b4ca99536b83f27ed07c5").
				addHeader("X-Response-Control", "minified").build();
		
		RestAssured.requestSpecification = videoGame_requestSpec;
		
		
		
		
		responseSpec = new ResponseSpecBuilder().
				expectStatusCode(200).
				build();
		RestAssured.responseSpecification = responseSpec;
				
	}

}
