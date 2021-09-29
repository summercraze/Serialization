/*
 * This code add a place and update it with a new address, and then it get the address to make sure the validation is working
 */
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddGetValidation 
{
	public static void main(String[] args) 
	{		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//add a place
		String body = "{\n" + 
				"  \"location\": {\n" + 
				"    \"lat\": -38.383494,\n" + 
				"    \"lng\": 33.427362\n" + 
				"  },\n" + 
				"  \"accuracy\": 50,\n" + 
				"  \"name\": \"Frontline house\",\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\n" + 
				"  \"types\": [\n" + 
				"    \"shoe park\",\n" + 
				"    \"shop\"\n" + 
				"  ],\n" + 
				"  \"website\": \"http://google.com\",\n" + 
				"  \"language\": \"French-IN\"\n" + 
				"}\n" + 
				"";
		String output = given().log().all().queryParam("key", "qaclick123")
					.header("Content-Type","application/json")
					.body(body).when().post("/maps/api/place/add/json").then()
					.header("Server", "Apache/2.4.18 (Ubuntu)") 
					.body("scope", equalTo("APP")) 
					.log().all().assertThat().statusCode(200).extract().response().asString();//get response as string
	    
	    //we need to parse the ID to input to other test
//	    JsonPath response = new JsonPath(output);//will take the response and parse it
//	    String placeId = response.getString("place_id"); //will get the placeID if path is parents would be parents.child(Ex:location.lat)
		//using new reusable method
	    String placeId = getJsonKey(output,"place_id");
	    
	    //update a place	  
	    String newAddress = "70 Summer walk, USA";
	    String updatePlaceBody = "{\r\n"
	    		+ "\"place_id\":\"" + placeId + "\",\r\n"
	    				+ "\"address\":\"" + newAddress +  "\",\r\n"
	    				+ "\"key\":\"qaclick123\"\r\n"
	    				+ "}";
	    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(updatePlaceBody)
	    .when().put("/maps/api/place/update/json")
	    .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));//make sure the msg is correct
	    
//	    //get the place and make sure the place is updated
//	    given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
//	    .when().get("maps/api/place/get/json")
//	    .then().assertThat().log().all().statusCode(200).body("address",equalTo(newAddress));
   
	    //Another way to get the address
	    //get the respond as string
	    String validateResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	    .when().get("maps/api/place/get/json")
	    .then().assertThat().log().all().statusCode(200).extract().response().asString();
	    //parse the respond as string
	    String responseAddress = getJsonKey(validateResponse,"address");
	    //JUnit and TestNG are use to testing
	    Assert.assertEquals(newAddress,responseAddress);//compare both string using testNG
	}
	
	//reusable function to get key
	public static String getJsonKey(String response, String key)
	{
		JsonPath jsResponse = new JsonPath(response);
		return jsResponse.getString(key);
	}
	
}
