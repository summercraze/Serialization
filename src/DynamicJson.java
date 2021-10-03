/*
 * This contain the main codes from each lesson
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
	
	//Same method but parameter would change every time
	@Test
	public void addBookWithParameter()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String successMessage = given()
				.body(payload.AddBook("Jane Eyle","abc","1234","Charlotte Bronte"))
				.when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
	    String ID = ReusableMethods.getJsonKey(successMessage, "ID");
	    System.out.println(ID);
		 
	}
	
	//Same method but use data from Data Provider
	@Test(dataProvider = "BooksData")
	public void addBookWithData(String title, String isbn, String aisle, String author)
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String successMessage = given()
				.body(payload.AddBook(title,isbn,aisle,author))
				.when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
	    String ID = ReusableMethods.getJsonKey(successMessage, "ID");
	    System.out.println(ID);
	}

	//Method to read data and delete book
	@Test(dataProvider = "BooksData")
	public void deleteBook(String title, String isbn, String aisle, String author)
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String bookID = isbn + aisle;
		String successMessage = given()
				.body(payload.DeleteBook(bookID))
				.when().post("/Library/DeleteBook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
	    String sucessDelete = ReusableMethods.getJsonKey(successMessage, "msg");
	    System.out.println(sucessDelete);
	}
	
	//Add book but take data from external JSON
	@Test
	public void addBookWithExternalFile() throws IOException
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String bookJson = new String(Files.readAllBytes(Paths.get("C:\\Users\\Rong\\Desktop\\self written code\\DynamicJSON\\Files\\book.json")));
		String successMessage = given()
				.body(bookJson)
				.when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
	    String ID = ReusableMethods.getJsonKey(successMessage, "ID");
	    System.out.println(ID);
		 
	}
	
	@DataProvider(name = "BooksData")
    public Object [][] getData()
    {
		//Multidimensional array
		//each array will hold data of the books
		//Will feed this to add books so it will run for however time of the length of array
		return new Object [][] {{"abc","efg","5678","hij"},{"klm","nop","9011","uvw"},{"xyz","bcd","1213","efg"}};
	}
	
}
