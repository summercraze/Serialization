/*
 * This contain the code to manipulate API of JIRA
 * This code will help to add attachment to issue
 */
import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class AddAttachment 
{
	public static void main(String[] args) 
	{	
		RestAssured.baseURI = "http://localhost:8080/";

		//authenticate
		SessionFilter session = new SessionFilter(); 
		String cookie = given().log().all()
				.header("Content-Type","application/json")
				.body(credentials.credentialBody()).filter(session).when().post("rest/auth/1/session").then()
				.log().all().assertThat().statusCode(200).extract().response().asString();
		
		//Add attachment
		given().log().all().pathParam("id", "10000")
		.header("X-Atlassian-Token","nocheck")
		.header("Content-Type","multipart/form-data")//since sending file header must also be different
		.filter(session)
		.multiPart("file", new File("C:\\Users\\Rong\\Desktop\\self written code\\JIRAAPI\\Files\\Attachment"))//method used to sent files, need to sent a file by using File class
		.when().post("rest/api/2/issue/{id}/attachments").then()
		.log().all().assertThat().statusCode(200);
	}
}
