package com.suntaragali.restapi;

import static io.restassured.RestAssured.get;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.http.Header;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersNCookies {
	
	/**
	 * Extract response headers
	 */
	@Test
	public void testResponseHeaders() {
		Response response = get("http://jsonplaceholder.typicode.com/photos");
		
		//to get single header
		String headerCFRAY = response.getHeader("CF-RAY");
		System.out.println(">>> Header : "+headerCFRAY);
		
		// to get all headers
		Headers headers = response.getHeaders();
		for(Header h : headers) {
			System.out.println(h.getName()+ ":" +h.getValue());
		}
		
	}
	
	/**
	 * Extract cookies
	 */
	@Test
	public void testCookies() {
		Response response = get("http://jsonplaceholder.typicode.com/photos");
		Cookie cookie = response.getDetailedCookie("__cfduid");
		System.out.println("Detailed : " + cookie.hasExpiryDate());
		System.out.println("Detailed : " + cookie.getExpiryDate());
		System.out.println("Detailed : " + cookie.hasValue());
	}

}
