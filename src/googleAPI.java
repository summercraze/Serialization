/*
 * This code add a place and update it with a new address with POJO
 */
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.location;
import POJO.locationBody;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class googleAPI 
{
	public static void main(String[] args) 
	{		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//serialize the body
		locationBody body = new locationBody();
		location location = new location();
		//set location
		location.setLat(-38.383494);
		location.setLng(33.427362);
		body.setLocation(location);
		body.setAccuracy(50);
		body.setName("Frontline house");
		body.setPhone_number("(+91) 983 893 3937");
		body.setAddress("29, side layout, cohen 09");
		//set array list
	    List<String> types = new ArrayList<String>();
	    types.add("shoe park");
	    types.add("shop");
	    body.setTypes( types);
	    
	    body.setWebsite("http://google.com");
	    body.setLanguage("French-IN");
	    
		String output = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body(body).when().post("/maps/api/place/add/json").then()
				.header("Server", "Apache/2.4.18 (Ubuntu)") 
				.body("scope", equalTo("APP")) 
				.log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(output);
	}
}
