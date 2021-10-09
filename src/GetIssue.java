
/*
 * This contain the code to manipulate API of JIRA
 * This code will help to get the issue and its information
 */
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class GetIssue
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI = "http://localhost:8080/";

		// authenticate
		SessionFilter session = new SessionFilter();
		given().log().all().header("Content-Type", "application/json").body(credentials.credentialBody())
				.filter(session).when().post("rest/auth/1/session").then().log().all().assertThat().statusCode(200);

		// get issue but just the comment field
		String comment = given().log().all().pathParam("id", "10000").queryParam("fields", "comment")// get only the comment field
				.header("Content-Type", "application/json").filter(session).when().get("rest/api/2/issue/{id}").then()//see how the query parameter hs not been in the url
				.log().all().assertThat().statusCode(200).extract().response().asString();
	}

}
