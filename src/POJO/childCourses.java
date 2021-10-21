/*
 * This contain the code to figure out how to deserialize the nested JSON in courses
 */
package POJO;

public class childCourses 
{
	    // all the keys
		private String webAutomation;
		private String api;
		private String mobile;
		
		//getter and setter for web automation
		public String getWebAutomation() 
		{
			return webAutomation;
		}
		public void setWebAutomation(String webAutomation) 
		{
			this.webAutomation = webAutomation;
		}
		
		//getter and setter for api
		public String getApi() 
		{
			return api;
		}
		public void setApi(String api) 
		{
			this.api = api;
		}
		
		//getter and setter for mobile
		public String getMobile() 
		{
			return mobile;
		}
		public void setMobile(String mobile) 
		{
			this.mobile = mobile;
		}
		
}
