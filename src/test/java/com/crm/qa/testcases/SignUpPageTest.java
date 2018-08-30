package com.crm.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.base.TestEnvironment;
import com.crm.pages.LoginPage;
import com.crm.pages.SignUpPage;

public class SignUpPageTest extends TestEnvironment {
	public static SignUpPage signUpPage;
	public static LoginPage loginPage;
	Select select;

	
	@BeforeClass(dependsOnMethods={"setUp"})
	public void initialization() throws InterruptedException, NullPointerException, IOException {
		//TestBase.driver.navigate().to(AppConfig.getURL());
		
		signUpPage = new SignUpPage();
		loginPage = new LoginPage();
		loginPage.clickSignUpLink();
	}

	@Test(priority = 1)
	public void verifySignUpCrmLogoTest() {
		// Reporter.log("verifySignUpCrmLogoTest");
		Assert.assertTrue(signUpPage.verifyCrmLogo());
	}

	@Test(priority = 2)
	public void verifyEditionTextTest() {
		// Reporter.log("verifyEditionTextTest");
		Assert.assertTrue(signUpPage.selectEditionDdText());
	}

	@Test(priority = 3)
	public void validateSignUpPageTest() throws InterruptedException {
		// Reporter.log("validateSignUpPageTest");
		signUpPage.selectEditionDd_Id("Free Edition");

	}

	@Test(priority = 4)
	public void signUpPageAllFieldsTest() throws InterruptedException {
		// Reporter.log("signUpPageAllFieldsTest");
		signUpPage.signUpPageAllFields();
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	public void verifySignUpFieldTextTest() {
		// Reporter.log("verifySignUpFieldTextTest");
		Assert.assertTrue(signUpPage.verifyFieldText());
	}

}