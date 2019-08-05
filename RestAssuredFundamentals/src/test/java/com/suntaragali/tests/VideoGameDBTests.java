package com.suntaragali.tests;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.suntaragali.config.EndPoint;
import com.suntaragali.config.TestConfig;
import com.suntaragali.restassured.VideoGame;

import io.restassured.response.Response;

public class VideoGameDBTests extends TestConfig {
	
	@Test
	public void getAllGames() {
		given().log().all().
		when().get(EndPoint.VIDEOGAMES).
		then().log().all();
	}

	@Test (enabled = false)
	public void createNewGameByJSON() {
		String gameBodyJson = "{\r\n" + 
				"  \"id\": 11,\r\n" + 
				"  \"name\": \"Need For Speed Hot Pursuit\",\r\n" + 
				"  \"releaseDate\": \"2019-08-01T06:33:08.506Z\",\r\n" + 
				"  \"reviewScore\": 50,\r\n" + 
				"  \"category\": \"Racing\",\r\n" + 
				"  \"rating\": \"Mature\"\r\n" + 
				"}";
		given().
			body(gameBodyJson).
			log().all().
		when().
			post(EndPoint.VIDEOGAMES).
		then().log().all();
	}
	
	@Test (enabled = false)
	public void createNewGamesByXML() {
		String gameBodyXml = "<videoGame category=\"Shooter\" rating=\"Universal\">\r\n" + 
				"    <id>13</id>\r\n" + 
				"    <name>Assassins Creed II</name>\r\n" + 
				"    <releaseDate>2005-10-01T00:00:00+05:30</releaseDate>\r\n" + 
				"    <reviewScore>97</reviewScore>\r\n" + 
				"  </videoGame>";
		given().
			body(gameBodyXml).
			log().all().
		when().
			post(EndPoint.VIDEOGAMES).
		then().
			log().all();
	}
	
	@Test (enabled = false)
	public void updateGame() {
		String gameBodyJson = "{\r\n" + 
				"  \"id\": 11,\r\n" + 
				"  \"name\": \"Need For Speed Most Wanted\",\r\n" + 
				"  \"releaseDate\": \"2019-08-01T06:33:08.506Z\",\r\n" + 
				"  \"reviewScore\": 50,\r\n" + 
				"  \"category\": \"Racing\",\r\n" + 
				"  \"rating\": \"Mature\"\r\n" + 
				"}";
		given().
			body(gameBodyJson).
		when().
			put("/videogames/11").
		then().
			log().all();
	}
	
	@Test (enabled = false)
	public void deleteGame() {
		given().
		when().
			delete("/videogames/11").
		then().log().all();
	}
	
	@Test (enabled=false)
	public void getSingleGame() {		
		given().
			pathParam("videoGameId", 5).
		when().
			get(EndPoint.SINGLE_VIDEOGAME).
		then().log().all();
	}
	
	@Test (enabled=false)
	public void testVideoGameSerializationByJSON() {
		VideoGame videoGame = new VideoGame("98", "2018-04-04","Mafia II","Mature","16","Shooter");
		given().
			body(videoGame).
			log().all().
		when().
			post(EndPoint.VIDEOGAMES).
		then().log().all();
	}
	
	@Test (enabled = false)
	public void testVideoGamesSchemaXML() {
		given().
			pathParam("videoGameId", 5).
		when().
			get(EndPoint.SINGLE_VIDEOGAME).
		then().
			body(matchesXsdInClasspath("VideoGames.xsd"));
	}
	
	@Test (enabled = false)
	public void testVideoGamesSchemaJSON() {
		given().
			pathParam("videoGameId", 2).
		when().
			get(EndPoint.SINGLE_VIDEOGAME).
		then().
			body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
	}
	
	@Test (enabled = false)
	public void convertJSONToPOJO() {
		Response response = 
				given().pathParam("videoGameId", 5).
				when().
					get(EndPoint.SINGLE_VIDEOGAME);
		VideoGame videoGame = response.getBody().as(VideoGame.class);
		System.out.println(videoGame.toString());
	}
	
}
