package com.suntaragali.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.suntaragali.config.EndPoint;
import com.suntaragali.config.TestConfig;

public class Sample extends TestConfig {

	@Test
	public void getVideoGamesById() {
		given().
			log().
			all().
		when().
			get("videogames/2").
		then().
			log().
			all();

	}
	
	@Test
	public void deleteGame() {
		given().
			spec(videoGame_requestSpec).
		when().
			delete("/videogames/11").
		then().
			spec(responseSpec).log().all();
	}
	
	@Test
	public void getAllGames() {
		given().when().
			get(EndPoint.VIDEOGAMES).then().log().all();
	}

}
