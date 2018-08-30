package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.Utility.TestUtil;
import com.crm.base.TestBase;
import com.crm.base.TestEnvironment;
import com.crm.pages.ContactsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

public class HomePageTest extends TestEnvironment {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;

	// test cases should be separated -- independent with each other
	// before each test case -- launch the browser and login
	// @test -- execute test case
	// after each test case -- close the browser
	/************* Initialization Page Class Variables *****************/
	@BeforeClass(dependsOnMethods={"setUp"})
	public void initialization() throws IOException {

		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(TestBase.prop.getProperty("username"), TestBase.prop.getProperty("password"));
		testUtil.switchToFrame();
	}

	@Test(priority = 1, description = "This will Test Title of the Home Page")
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home page title not matched");
	}

	@Test(priority = 2, description = "This will Test User Name Label present on the Home Page",enabled=true)
	public void verifyUserNameTest() {
		// testUtil.switchToFrame();
		TestUtil.highLighter(TestBase.driver, HomePage.userNameLabel);
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}

	@Test(priority = 3, description = "This will Test click on the contacts link and lands onthe contactsPage",enabled=true)
	public void verifyContactsLinkTest() {
		contactsPage = homePage.clickOnContactsLink();
	}

}