package com.suntaragali.restapi;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class ResponseTime {

	/**
	 * Measure the response time, Time include HTTP round trip + rest assured processing time
	 */
	@Test
	public void testResponseTime() {
		long t = given().get("http://jsonplaceholder.typicode.com/photos/1").time();
		System.out.println("Time (ms):" + t);
	}
}
