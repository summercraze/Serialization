/*
 * This contain the code to figure out how to deserialize the response within courses
 */
package POJO;

public class courseWebAutomation
{
	 // all the keys
	private String courseTitle;
	private String price;
	
	//getter and setter for course title
	public String getCourseTitle() 
	{
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle)
	{
		this.courseTitle = courseTitle;
	}
	//getter and setter for price
	public String getPrice() 
	{
		return price;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}
}
