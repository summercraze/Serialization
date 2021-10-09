/*
 * This contain the code to manipulate API of JIRA
 * This code will help to update comment
 */
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class UpdateComments 
{
	public static void main(String[] args) 
	{	
		RestAssured.baseURI = "http://localhost:8080/";
		
		//authenticate scenario
		SessionFilter session = new SessionFilter(); //automatically filter code of this session and you can use it in all subsequent session
		String cookie = given().log().all()
				 .header("Content-Type","application/json")
				.body(credentials.credentialBody()).filter(session).when().post("rest/auth/1/session").then()
				.log().all().assertThat().statusCode(200).extract().response().asString();
		 
		//add comment use path parameter instead of query parameter
		given().log().all().pathParam("id", "10000")
		.header("Content-Type","application/json")
		.body(credentials.newCommentBody()).filter(session).when().post("rest/api/2/issue/{id}/comment").then()
		.log().all().assertThat().statusCode(201);
	}
}
