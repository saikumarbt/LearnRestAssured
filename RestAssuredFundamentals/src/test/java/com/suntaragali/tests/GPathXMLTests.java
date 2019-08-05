package com.suntaragali.tests;

import static io.restassured.RestAssured.get;

import java.util.List;

import org.testng.annotations.Test;

import com.suntaragali.config.EndPoint;
import com.suntaragali.config.TestConfig;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;

public class GPathXMLTests extends TestConfig {

	@Test
	public void getFirtGameInList() {
		Response response = get(EndPoint.VIDEOGAMES);
		String name = response.path("videoGames.videoGame.name[0]");
		System.out.println(name);
	}
	
	@Test
	public void getAttributeName() {
		Response response = get(EndPoint.VIDEOGAMES);
		String category = response.path("videoGames.videoGame[0].@category");
		System.out.println(category);
	}
	
	@Test
	public void getListOfXmlNodes() {
		String responseAsString = get(EndPoint.VIDEOGAMES).asString();
		List<Node> allResults = XmlPath.from(responseAsString).get("videoGames.videoGame.findAll{element-> return element}");
		System.out.println(allResults.get(2).get("name"));
	}
	
	@Test
	public void getListOfXmlNodesByFindAllOnAttributes() {
		String responseAsString = get(EndPoint.VIDEOGAMES).asString();
		List<Node> allDrivingGames = XmlPath.from(responseAsString).get("videoGames.videoGame.findAll{videoGame -> def category = videoGame.@category; category=='Driving'}");
		System.out.println(allDrivingGames.get(0).get("name").toString());
;	}
	
	@Test
	public void getSingleNode() {
		String responseAsString = get(EndPoint.VIDEOGAMES).asString();
		Node videoGame = XmlPath.from(responseAsString).get("videoGames.videoGame.find{videoGame -> def name = videoGame.name; name == 'Tetris'}");
		System.out.println(videoGame.get("name").toString());
	}
	
	@Test
	public void getSingleElementDepthFirst() {
		String responseAsString = get(EndPoint.VIDEOGAMES).asString();
		int reviewScore = XmlPath.from(responseAsString).getInt("**.find{it.name=='Gran Turismo 3'}.reviewScore");
		System.out.println(reviewScore);
	}
	
	@Test
	public void getAllNodesBasedOnCondition() {
		String responseAsString = get(EndPoint.VIDEOGAMES).asString();
		int reviewScore = 90;
		List<Node> allVideoGamesOverCertainScore = XmlPath.from(responseAsString).get("videoGames.videoGame.findAll{it.reviewScore.toFloat() >= "+reviewScore+"}");
		System.out.println(allVideoGamesOverCertainScore);
	}
	
}
