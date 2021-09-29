import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class Basic {
public static void main(String[] args) 
{
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
	String output = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body("{\n" + 
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
						"").when().post("/maps/api/place/add/json").then()
				.header("Server", "Apache/2.4.18 (Ubuntu)") //make sure header is from the right place(authentication //output instead of input
				.body("scope", equalTo("APP")) //uses the hamcrest package //check whether body scope equal to app
				.log().all().assertThat().statusCode(200).extract().response().asString();
	 
		System.out.println(output);
		
	}

}
