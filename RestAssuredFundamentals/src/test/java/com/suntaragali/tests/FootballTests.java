package com.suntaragali.tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.Test;

import com.suntaragali.config.TestConfig;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class FootballTests extends TestConfig {
	
	private Headers headers;

	@Test (enabled = false)
	public void getAllCompetitionsOneSeason() {
		given().
			spec(football_requestSpec).
			//queryParam("season", 2016).
		when().
			get("competitions/2016").
		then().log().all();
	}
	
	@Test (enabled = false)
	public void getTeamCount_OneComp() {
		given().
			spec(football_requestSpec).
		when().
			get("teams").
		then().
			body("count", equalTo(48));
	}
	
	@Test (enabled = false)
	public void getFirstTeamName() {
		given().
		spec(football_requestSpec).
		when().
			get("teams").
		then().
			body("teams.id[0]", equalTo (57));
	}
	
	@Test (enabled=false)
	public void getAllTeamData() {
		String responseBody = given().spec(football_requestSpec).when().get("teams").then().extract().asString();
		System.out.println(responseBody);
	}
	
	@Test (enabled = false)
	public void getAllTeamData_DoCheckFirst() {
		Response response = 
				given().
					spec(football_requestSpec).
				when().
					get("teams").
				then().
					contentType(ContentType.JSON).
					extract().response();
		String jsonReponseAsString = response.asString();
		System.out.println(jsonReponseAsString);
	}
	
	@Test
	public void extractHeaders() {
		Response response = 
				given().
					spec(football_requestSpec).
				when().
					get("teams").
				then().
					contentType(ContentType.JSON).
					extract().response();
		headers = response.getHeaders();
		String contentType = response.getHeader("Content-Type");
		System.out.println(contentType);
	}
	
	@Test
	public void extractFirstTeamName() {
		String firstTeamName = 
				given().
					spec(football_requestSpec).
				when().
					get("teams").
					jsonPath().getString("teams.shortName[0]");
		System.out.println(firstTeamName);
		
	}
	
	@Test
	public void extractAllTeamNames() {
		Response response = 
				given().
				spec(football_requestSpec).
			when().
				get("teams").
			then().
				contentType(ContentType.JSON).
				extract().response();
		List<String>teamNames = response.jsonPath().get("teams.name");
		for (String teamName: teamNames) {
			System.out.println(teamName);
		}
	}
}
