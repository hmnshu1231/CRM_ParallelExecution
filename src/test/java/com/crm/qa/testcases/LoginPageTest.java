package com.crm.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.base.TestEnvironment;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.SignUpPage;

public class LoginPageTest extends TestEnvironment {
	

	LoginPage loginPage;
	HomePage homePage;
	SignUpPage signUpPage;

	/*************Initialization Page Class Variables*****************/
	@BeforeClass
	public void initialization() throws IOException {
		//TestBase.driver.navigate().to(AppConfig.getURL());
		//signUpPage = new SignUpPage();
		loginPage = new LoginPage();
		//loginPage.clickSignUpLink();
	}
	@Test(priority = 1)
	public void loginPageTitleTest() {
		
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");
	}

	@Test(priority = 2)
	public void crmLogoImageTest() {

		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3, expectedExceptions = NoSuchElementException.class)
	public void signUpBtnTest() {
		loginPage.validateSignUpBtn();

	}

	@Test(priority = 4)
	public void forgotPassLinkTest() {

		Assert.assertTrue(loginPage.validateForgotPassLink());
	}

	@Test(priority = 5)
	public void allLinkTexts() {

		Assert.assertTrue(loginPage.validateLinkTexts());
	}

	// retryAnalyzer=Retry.class
	@Test(priority = 6, groups = "LoginPage Logo Details")
	public void CRMlogosTest() {
		Assert.assertTrue(loginPage.validateCRMlogos());
	}

	@Test(priority = 7, groups = "LoginPage Logo Details")
	public void validateCRMlogoText() {
		Assert.assertTrue(loginPage.validateCRMlogosText());
	}

	@Test(priority = 8, groups = "LoginPage Logo Details")
	public void validateCRMHeaderTextTest() {
		Assert.assertTrue(loginPage.validateCRMHeaderText());

	}

	@Test(priority = 9, groups = "LoginPage Logo Details")
	public void validateCRMLogoDescriptionTest() {
		System.out.println(loginPage.validatCRM_Description());
		String expectedDescription = "Keep a single shared customer profile, company-wide. Know you're looking at the same "
				+ "up-to-date contact as service and marketing. Gain insight through a shared database system -- that's CRM.";

		Assert.assertEquals(loginPage.validatCRM_Description(), expectedDescription, "CRM description not matched");
	}

	@Test(priority = 10, groups = "LoginPage Logo Details")
	public void validateSalesPipelineDescriptionTest() {
		System.out.println(loginPage.salesPipeline_Description());
		String expectedDescription = "Get all the details on all your team's deals – deal stage, products, competition, quotes "
				+ "and more. See where leads come from and stay connected to the people "
				+ "and information you need to close every sale.";

		Assert.assertEquals(loginPage.salesPipeline_Description(), expectedDescription,
				"salesPipeline description not matched");
	}

	@Test(priority = 11, groups = "LoginPage Logo Details")
	public void validateMarketingAutomationDescriptionTest() {
		System.out.println(loginPage.marketingAutomation_Description());
		String expectedDescription = "Powerful e-mail, SMS, and print marketing tools including email marketing campaigns with "
				+ "plain text or HTML emails and attachments or autoresponders. We also do print campaign and direct mail.";

		Assert.assertEquals(loginPage.marketingAutomation_Description(), expectedDescription,
				"marketing automation description not matched");
	}

	@Test(priority = 12, groups = "LoginPage Logo Details")
	public void validateGetMobileDescriptionTest() {
		System.out.println(loginPage.getMobile_Description());
		String expectedDescription = "Works on any device – just fire up your mobile browser and connect to our secure website. "
				+ "Bookmark Free CRM so you can have quick access every time; over 20% of our users are "
				+ "on mobile tablets and smartphones.";

		Assert.assertEquals(loginPage.getMobile_Description(), expectedDescription,
				"get mobile description not matched");
	}

	@Test(priority = 13, enabled = true)
	public void loginTest() {
		try {
			homePage = loginPage.login(TestBase.prop.getProperty("username"), TestBase.prop.getProperty("password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}