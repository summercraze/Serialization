/*
 * This contain all codes that could be reused
 */
import io.restassured.path.json.JsonPath;

public class ReusableMethods 
{
	//simple code to get the key
	public static String getJsonKey(String response, String key)
	{
		JsonPath jsResponse = new JsonPath(response);
		return jsResponse.getString(key);
	}
	
}
