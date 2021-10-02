/*
 * This contain the main codes from each lesson
 */
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class DynamicJson 
{
	//simple code to test that book is added
	@Test
	public void addBook()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String successMessage = given()
				.body(payload.AddBook())
				.when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
	    String ID = ReusableMethods.getJsonKey(successMessage, "ID");
	    System.out.println(ID);
		 
	}
}
