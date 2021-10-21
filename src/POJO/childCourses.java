/*
 * This contain the code to figure out how to deserialize the nested JSON in courses
 */
package POJO;

import java.util.List;

public class childCourses 
{
	    // all the keys
		private List <courseWebAutomation> webAutomation; //will dynamic expland the lsit
		private List <courseAPI>  api;
		private courseMobile  mobile;
		
		//getter and setter for web automation
		public List<courseWebAutomation> getWebAutomation() 
		{
			return webAutomation;
		}
		public void setWebAutomation(List<courseWebAutomation> webAutomation) 
		{
			this.webAutomation = webAutomation;
		}
		
		//getter and setter for api
		public List<courseAPI> getApi() 
		{
			return api;
		}
		public void setApi(List<courseAPI> api) 
		{
			this.api = api;
		}
		
		//getter and setter for mobile
		public courseMobile getMobile() 
		{
			return mobile;
		}
		public void setMobile(courseMobile mobile) 
		{
			this.mobile = mobile;
		}
		
}
