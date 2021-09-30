/*
 * This code mock a JSON response in order to test nested JSON response
 */
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class mockJS 
{
	public static void main(String[] args) 
	{		
		
		String mockResponse = "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 1162,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    },\r\n" + 
				"     {\r\n" + 
				"      \"title\": \"Appium\",\r\n" + 
				"      \"price\": 36,\r\n" + 
				"      \"copies\": 7\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"  ]\r\n" + 
				"}\r\n" + 
				"";
		
		//now we will extract the mock resposne
		JsonPath parsedResponse = new JsonPath(mockResponse);//will take the response and parse it
		
		//Test 1:  Print No of courses returned by API
		//The .size() function is available in Jquery and many other libraries.
		//The .length property works only when the index is an integer.
		int courseCount = parsedResponse.getInt("courses.size()"); //since course is array, you can get the size of the array
		System.out.println("Test 1 " + courseCount);
 
		//Test 2: Print Purchase Amount
		int purchaseAmount = parsedResponse.getInt("dashboard.purchaseAmount");
		System.out.println("Test 2 " + purchaseAmount);
		
		
		//Test 3: Print Title of the first course
        String titleFirstCourse = parsedResponse.getString("courses[0].title");
        System.out.println("Test 3 " + titleFirstCourse);
        
        //Test 4: Print All course titles and their respective Prices
        System.out.println("Test 4");
        for(int i = 0; i <courseCount; i++)
        {
        	String courseTitle = parsedResponse.getString("courses["+i+"].title");//how to get i in the string
         	int coursePrice = parsedResponse.getInt("courses["+i+"].price");
            System.out.println(courseTitle + ":" +  coursePrice);
        }
	    
        //Test 5: Print no of copies sold by RPA Course
        System.out.println("Test 5");
        for(int i = 0; i <courseCount; i++)
        {
        	String courseTitle = parsedResponse.getString("courses["+i+"].title");
         	if(courseTitle.equalsIgnoreCase( "RPA"))
         	{
         		int courseCopyRPA = parsedResponse.getInt("courses["+i+"].copies");
         		System.out.println(courseTitle + ":" +  courseCopyRPA );
         		break;//optimize code
         	}    	           
        }
        
        //Test 6: Verify if Sum of all Course prices matches with Purchase Amount
        System.out.println("Test 6");
        int sum = 0;
        for(int i = 0; i <courseCount; i++)
        {
        	int courseCopy = parsedResponse.getInt("courses["+i+"].copies");  
        	int coursePrice = parsedResponse.getInt("courses["+i+"].price");
        	sum += (courseCopy * coursePrice);
        	
        }
        System.out.println(sum);
	}
	
}
