package com.suntaragali.tests;

import static io.restassured.RestAssured.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.suntaragali.config.TestConfig;

import io.restassured.response.Response;

public class GPathJSONTests extends TestConfig {

	@Test
	public void extractMapOfElementsWithFind() {
		Response response = get("teams");
		
		Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find{it.name == 'Arsenal FC'}");
		System.out.println(allTeamDataForSingleTeam);
	}
	
	@Test
	public void extractSingleValueWithFind() {
		Response response = get("teams");
		String certainTeamEmail = response.path("teams.find{it.name == 'Arsenal FC'}.email");
		System.out.println(certainTeamEmail);
	}
	
	@Test
	public void extractListOfValuesWithFindAll() {
		Response response = get("teams");
		List<String> teamNames = response.path("teams.findAll{it.id>0}.name");
		System.out.println(teamNames);
	}
	
	@Test
	public void extractSingleValueWithHighestNumber() {
		Response response = get("teams");
		String teamName = response.path("teams.max{it.id}.name");
		System.out.println(teamName);
	}
	
	@Test
	public void extractMultipleValuesAndSumThem() {
		Response response = get("teams");
		int sumOfId = response.path("teams.collect{it.id}.sum()");
		System.out.println(sumOfId);
	}
	
	@Test
	public void extractMapOfObjectWithFindAndFindAll() {
		String position = "Goalkeeper";
		String nationality = "Argentina";
		
		Response response = get("teams/66/");
		Map<String, ?> playerOfCertainPosition = response.path("squad.findAll{it.position == '%s'}.find{it.nationality =='%s'}", position, nationality);
		System.out.println(playerOfCertainPosition);
	}
	
	@Test
	public void extractMultiplePlayers() {
		String position = "Goalkeeper";
		String nationality = "England";
		
		Response response = get("teams/66/");
		ArrayList<Map<String, ?>> allPlayersCertainNation = 
				response.path("squad.findAll{it.position == '%s'}.findAll{it.nationality =='%s'}", position, nationality);
		System.out.println(allPlayersCertainNation);
	}
}
