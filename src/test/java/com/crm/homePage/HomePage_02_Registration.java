package com.crm.homePage;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.Utility.AppConfig;
import com.crm.base.TestBase;
import com.crm.base.TestEnvironment;
import com.crm.testData.ExcelReader;

public class HomePage_02_Registration extends TestEnvironment 
{
	
	@DataProvider(name="registration data")
	public Object[][] passData() throws IOException
	{
		return ExcelReader.getExcelData(AppConfig.getExcelPath(), "Login");
	}
	
	
	@Test(dataProvider = "registration data")
	public void userRegistration(String userId,String pwd, String confirmPwd) throws Exception
	{ 
		TestBase.loadPropertiesFile();
		TestBase.driver.navigate().to(AppConfig.getURL());
		Thread.sleep(2000);
	    
		WebElement register = TestBase.getWebElement("RegisterButton");		
	    JavascriptExecutor js = (JavascriptExecutor)TestBase.driver;
		js.executeScript("arguments[0].setAttribute('target','_self');",register);
	    register.click(); 
	    
	    TestBase.getWebElement("UserID").sendKeys(userId);
	    TestBase.getWebElement("Password").sendKeys(pwd);
	    TestBase.getWebElement("ConfirmPassword").sendKeys(confirmPwd);
	    TestBase.getWebElement("Submit").click();
	    
	    Thread.sleep(2000);
	}
}