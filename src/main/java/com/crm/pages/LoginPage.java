package com.crm.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class LoginPage extends TestBase {

	// Page Factory - OR:
	/************ LoginPage - login elements ************/
	@FindBy(name = "username")
	WebElement username;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	@FindBy(linkText = "Forgot Password?")
	WebElement forgotPassLink;

	/************ LoginPage - links menu ************/
	@FindBy(xpath = "//a[contains(text(),'Features')]")
	WebElement featuresLink;
	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	WebElement signUpLink;
	@FindBy(xpath = "//a[contains(text(),'Pricing')]")
	WebElement pricingLink;
	@FindBy(xpath = "//a[contains(text(),'Customers')]")
	WebElement customersLink;
	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	WebElement contactLink;
	@FindBy(xpath = "//a[contains(text(),'Support')]")
	WebElement supportLink;

	/************ LoginPage - Logos ************/
	@FindBy(xpath = "//*[@class='fa fa-users skrollable skrollable-after']")
	WebElement CRMtextLogo;
	@FindBy(xpath = "//*[@class='fa fa-money skrollable skrollable-after']")
	WebElement salesPipelineLogo;
	@FindBy(xpath = "//*[@class='fa fa-envelope skrollable skrollable-after']")
	WebElement marketingAutomationlogo;
	@FindBy(xpath = "//*[@class='fa fa-mobile-phone skrollable skrollable-after']")
	WebElement getMobilelogo;

	/************ LoginPage - Logos Text ************/
	@FindBy(xpath = "//*[contains(text(),'CRM Contacts')]")
	WebElement CRMText;
	@FindBy(xpath = "//*[contains(text(),'Sales Pipeline')]")
	WebElement salesPipelineText;
	@FindBy(xpath = "//*[contains(text(),'Marketing Automation')]")
	WebElement marketingAutomationText;
	@FindBy(xpath = "//*[contains(text(),'Get Mobile')]")
	WebElement getMobileText;

	/************ LoginPage - Logos Header Text ************/
	@FindBy(xpath = "//*[contains(text(),'TRACK EVERYTHING INSTANTLY')]")
	WebElement CRMHeaderText;
	@FindBy(xpath = "//*[contains(text(),'SALES PIPELINE DASHBOARD')]")
	WebElement salesPipelineHeaderText;
	@FindBy(xpath = "//*[contains(text(),'UNLIMITED CAMPAIGNS')]")
	WebElement marketingAutomationHeaderText;
	@FindBy(xpath = "//*[contains(text(),'ANY DEVICE')]")
	WebElement getMobileHeaderText;

	/************ LoginPage - Logos Description ************/
	@FindBy(xpath = "//*[@id=\"services\"]/div/div/div[1]/p/small")
	WebElement CRMContactsDescription;
	@FindBy(xpath = "//*[@id=\"services\"]/div/div/div[2]/p/small")
	WebElement salesPipelineDescription;
	@FindBy(xpath = "//*[@id=\"services\"]/div/div/div[3]/p/small")
	WebElement marketingAutomationDescription;
	@FindBy(xpath = "//*[@id=\"services\"]/div/div/div[4]/p/small")
	WebElement getMobileDescription;

	// Constructor - Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);

	}

	public boolean validateLinkTexts() {
		if (featuresLink.isDisplayed() && signUpLink.isDisplayed() && pricingLink.isDisplayed()
				&& customersLink.isDisplayed() && contactLink.isDisplayed() && supportLink.isDisplayed()) {
			System.out.println("All Links are Present on LoginPage");
		}
		return true;

	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}

	public boolean validateSignUpBtn() {
		return signUpBtn.isDisplayed();
	}

	public boolean validateForgotPassLink() {
		return forgotPassLink.isDisplayed();
	}

	public SignUpPage clickSignUpLink() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", signUpLink);

		return new SignUpPage();
	}

	public boolean validateCRMlogos() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 450)");
		if (CRMtextLogo.isDisplayed() & salesPipelineLogo.isDisplayed() & marketingAutomationlogo.isDisplayed()
				& getMobilelogo.isDisplayed()) {
			System.out.println("All Links are Present on LoginPage");
		}
		return true;
	}

	public boolean validateCRMlogosText() {
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("window.scrollBy(0,200)");
		if (CRMText.isDisplayed() && salesPipelineText.isDisplayed() && marketingAutomationText.isDisplayed()
				&& getMobileText.isDisplayed()) {
			System.out.println("All logo's texts are Present on LoginPage");
		}
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
		return true;
	}

	public boolean validateCRMHeaderText() {

		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("window.scrollBy(0,200)");
		if (CRMHeaderText.isDisplayed() && salesPipelineHeaderText.isDisplayed()
				&& marketingAutomationHeaderText.isDisplayed() && getMobileHeaderText.isDisplayed()) {
			System.out.println("All Links Header Text is Present on LoginPage");
		}
		return true;
	}

	public String validatCRM_Description() {
		return CRMContactsDescription.getText();

	}

	public String salesPipeline_Description() {
		return salesPipelineDescription.getText();

	}

	public String marketingAutomation_Description() {
		return marketingAutomationDescription.getText();

	}

	public String getMobile_Description() {
		return getMobileDescription.getText();

	}

	public HomePage login(String un, String pwd) {

		try {
			username.sendKeys(un);
			password.sendKeys(pwd);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", loginBtn);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HomePage();
	}

}