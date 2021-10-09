import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class ValidateComment 
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI = "http://localhost:8080/";
        String expectedMessage = "Love goes is such a good album";
		
        // authenticate
		SessionFilter session = new SessionFilter();
		given().log().all().header("Content-Type", "application/json").body(credentials.credentialBody())
				.filter(session).when().post("rest/auth/1/session").then().log().all().assertThat().statusCode(200);
		
		//Add an issue and retrieve its comment id
		String newComment = given().log().all().pathParam("id", "10000")
		.header("Content-Type","application/json")
		.body(credentials.additionalCommentBody()).filter(session).when().post("rest/api/2/issue/{id}/comment").then()
		.log().all().assertThat().statusCode(201).extract().response().asString();
		String commentID = ReusableMethods.getJsonKey(newComment,"id");
		
		// get issue but just the comment field
		String getComment = given().log().all().pathParam("id", "10000").queryParam("fields", "comment")// get only the comment field
				.header("Content-Type", "application/json").filter(session).when().get("rest/api/2/issue/{id}").then()//see how the query parameter hs not been in the url
				.log().all().assertThat().statusCode(200).extract().response().asString();
		
		//get the comment loop
		JsonPath jsResponse = new JsonPath(getComment);
		int length = jsResponse.getInt("fields.comment.comments.size()");
		
		//loop check the id and extract the body
		for (int i = 0 ; i < length; i++)
		{
			//check if the id are same as the new comment id
			if(commentID == jsResponse.get("fields.comment.comments["+i+"].id").toString())//the get will just get the response and toString just convert everything to String
			{
				String actualMessage = jsResponse.get("fields.comment.comments["+i+"].body").toString();//this get the actual message
				Assert.assertEquals(expectedMessage,actualMessage);
				break;
			}
		}
	}
}
