/*
 * This contain all the body
 */

public class payload {

	//body to add a book
	public static String AddBook()
	
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"LOL\",\r\n"
				+ "\"isbn\":\"WHAT\",\r\n"
				+ "\"aisle\":\"123\",\r\n"
				+ "\"author\":\"You should tell us you need unique KEY\"\r\n"
				+ "}\r\n"
				+ "";		
		
	}
	
    public static String AddBook(String title, String isbn, String aisle, String author)
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"" + title + "\",\r\n"
				+ "\"isbn\":\"" + isbn + "\",\r\n"
				+ "\"aisle\":\"" + aisle + "\",\r\n"
				+ "\"author\":\"" + author + "\"\r\n"
				+ "}\r\n"
				+ "";				
	}
	
    public static String DeleteBook(String id)
   	{
   		return "{\r\n"
   				+ " \r\n"
   				+ "\"ID\" : \"" + id + "\"\r\n"
   				+ " \r\n"
   				+ "} \r\n"
   				+ "";				
   	}
}
