/*
 * This contain the code to figure out how to automation Auth2.0
 */
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class getCourses 
{
	public static void main(String[] args) throws InterruptedException 
	{	
		//open up the get token website with selenium
		System.setProperty("webdriver.chrome.driver",credentials.chromeDriverLocation());
		WebDriver driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.get(credentials.authURL());
		driver.findElement(By.cssSelector("input.whsOnd.zHQkBf")).sendKeys(credentials.email());
		driver.findElement(By.cssSelector("input.whsOnd.zHQkBf")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input.whsOnd.zHQkBf")).sendKeys(credentials.emailPassword());
		driver.findElement(By.cssSelector("input.whsOnd.zHQkBf")).sendKeys(Keys.ENTER);
		Thread.sleep(6000);
		String codeURL = driver.getCurrentUrl();
		driver.close();
		driver.quit();
		
		//Extrat the url to find the token
		String code = codeURL.split("code=")[1].split("&scope")[0];//Get the later part split by keyword code and then by the &scope
		
		 //Extract the access token from the response server
		String accessTokenResponse = given().urlEncodingEnabled(false).log().all()//will change the special character
		.queryParam("code", code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")// don't know the code since we have yet authorized so lets leave it blank
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		
		//Get the course by giving access token
		String courseResponse = given().log().all().queryParam("access_token", ReusableMethods.getJsonKey(accessTokenResponse,"access_token"))//will get access token later
		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(courseResponse);
	
	}
}
