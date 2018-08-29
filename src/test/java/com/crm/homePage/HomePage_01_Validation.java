package com.crm.homePage;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.Utility.AppConfig;
import com.crm.base.TestBase;
import com.crm.base.TestEnvironment;



public class HomePage_01_Validation extends TestEnvironment
{
  @Test
  public void homePageValidation() throws Exception 
  {	  
	  TestBase.loadPropertiesFile();
	  TestBase.driver.navigate().to(AppConfig.getURL());
	  
	  WebElement logo = TestBase.getWebElement("HomePageLogo");
	  WebElement findflight = TestBase.getWebElement("FindAFlight");
	  
	  if(logo.isDisplayed()&& findflight.isEnabled())
	  {	
		  System.out.println(findflight.getAttribute("alt"));
		  System.out.println(logo.getAttribute("alt"));
	  }
  }
}