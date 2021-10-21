/*
 * This contain the code to figure out how to deserialize the response with its major key
 */
package POJO;

public class deserializeCourse 
{ 
	// all the keys
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private childCourses courses;
	private String linkedIn;
	
	//Getter and setter for instructor // can use alt shift s to generate
	public String getInstructor() 
	{
		return instructor;
	}
	public void setInstructor(String instructor) 
	{
		this.instructor = instructor;
	}
	
	//getter and setter for url
	public String getUrl() 
	{
		return url;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	//getter and setter for service
	public String getServices() 
	{
		return services;
	}
	public void setServices(String services) 
	{
		this.services = services;
	}
	
	//getter and setter for expertise
	public String getExpertise() 
	{
		return expertise;
	}
	public void setExpertise(String expertise) 
	{
		this.expertise = expertise;
	}
	
	//getter and setter for course
	public childCourses getCourses() 
	{
		return courses;
	}
	public void setCourses(childCourses courses) 
	{
		this.courses = courses;
	}
	
	//getter and setter for linkedin
	public String getLinkedIn() 
	{
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) 
	{
		this.linkedIn = linkedIn;
	}
}
