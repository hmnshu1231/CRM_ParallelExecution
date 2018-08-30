package com.crm.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.crm.Utility.AppConfig;
import com.crm.Utility.WaitHelper;
import com.crm.report.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;

public class TestEnvironment {

	@Parameters("myBrowser")

	@BeforeClass(alwaysRun = true)
	public void setUp(@Optional String myBrowser) throws InterruptedException, NullPointerException, IOException {
		System.out.println("*****************************************************");
		System.out.println("launching Browser");
		TestBase.appLoadPropertiesFile();

		if (this.Parameters(myBrowser)) {
			TestBase.getBrowser(myBrowser);
		} else {
			TestBase.getBrowser(AppConfig.getBrowser());
		}
		TestBase.driver.get(AppConfig.getURL());
		// TestBase.getBrowser(myBrowser);
		// TestBase.getBrowser(AppConfig.getBrowser());
		// TestBase.driver.navigate().to(AppConfig.getURL());
		WaitHelper.setImplicitWait(20);
		WaitHelper.setPageLoadTimeout(20);

	}

	private boolean Parameters(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	@BeforeTest
	public void testStart() throws IOException {
		ExtentReport.extent.addSystemInfo("Project Name", "Himanshu CRM Project");
		ExtentReport.extent.addSystemInfo("Time Zone", System.getProperty("user.timezone"));
		ExtentReport.extent.addSystemInfo("User Location", System.getProperty("user.country"));
		ExtentReport.extent.addSystemInfo("OS version", System.getProperty("os.version"));
		ExtentReport.extent.addSystemInfo("Java Version", System.getProperty("java.version"));
		ExtentReport.extent.loadConfig(new File(AppConfig.getExtent_ConfigXML()));
	}

	@BeforeMethod()
	public static void beforeMethod(Method result) {
		ExtentReport.test = ExtentReport.extent.startTest(result.getName());
		ExtentReport.test.log(LogStatus.INFO, result.getName() + " Test has Started");
		// ExtentReport.test.log(LogStatus.INFO, onTestStart(result));
		// ExtentReport.test.getDescription();
		// =result.getName().getClass().getAnnotations();
		// String str = result.getName().getClass().getMethods().toString();
		ExtentReport.test.assignAuthor("Himanshu Malviya QA");
		ExtentReport.test.assignCategory("Sanity :: " + "DEV" + " :: API VERSION - " + "2.00");

	}

	@AfterMethod
	public static void afterMethod(ITestResult result) throws IOException {
		ExtentReport report = new ExtentReport();
		report.getResult(result);
	}

	@AfterTest
	public void testResult() {
		ExtentReport.extent.endTest(ExtentReport.test);
		ExtentReport.extent.flush();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		// if (TestBase.driver != null) {
		System.out.println("Closing the Browser");
		System.out.println("*****************************************************");

		// TestBase.driver.close();
		TestBase.driver.quit();
		// }
	}
}