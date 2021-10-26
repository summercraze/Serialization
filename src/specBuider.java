import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import POJO.location;
import POJO.locationBody;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class specBuider 
{
	public static void main(String[] args) 
	{		
		//specBuilder the request
		RequestSpecification commonRequest  = new RequestSpecBuilder().setContentType(ContentType.JSON)
		           .setBaseUri("https://rahulshettyacademy.com")
		          .addQueryParam("key", "qaclick123") .build();
		ResponseSpecification commonResponse = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
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
	    
	    //set up request specific with the body
	    RequestSpecification request =  given().log().all().spec(commonRequest)
					.body(body);
	    //calling the request
		Response response = request.when().post("/maps/api/place/add/json")
				.then().spec(commonResponse)
				.extract().response();
	   System.out.println(response.asString());
	}
}


