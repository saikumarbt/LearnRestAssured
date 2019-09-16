package com.suntaragali.restapi;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class SettingRootNode {
	/**
	 * Test without having root setting configured
	 */
	@Test
	public void verifyWithoutRoot() {
		given().
			get("https://reqres.in/api/users").
		then().
			body("data.id[0]", is(1)).
			body("data.email[0]", is("george.bluth@reqres.in")).
			body("data.first_name[0]", is("George"));
	}
	
	/**
	 * Testing using root feature
	 */
	@Test
	public void vefiyWithRoot() {
		
		given().
		get("https://reqres.in/api/users").
	then().
		root("data").
		body("id[0]", is(1)).
		body("email[0]", is("george.bluth@reqres.in")).
		body("first_name[0]", is("George"));
		
	}
	
	/**
	 * Detaching the root in between
	 */

	@Test
	public void detachRoot() {
		
		given().
		get("https://reqres.in/api/users").
	then().
		root("data").
		body("id[0]", is(1)).
		body("email[0]", is("george.bluth@reqres.in")).
		detachRoot("data").
		body("first_name[0]", is("George"));
		
	}
	
}
