package com.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.base.TestBase;

public class SignUpPage extends TestBase {

	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement signUpCrmLogo;

	@FindBy(xpath = "//div[contains(text(),'Select edition above')]")
	WebElement selectEditionDD_Text;

	@FindBy(xpath = "//select['payment_plan_id']")
	WebElement editionPaymentPlanId;

	@FindBy(name = "first_name")
	WebElement firstN;

	@FindBy(name = "surname")
	WebElement lastN;

	@FindBy(name = "email")
	WebElement emailId;

	@FindBy(name = "email_confirm")
	WebElement confirmEmailId;

	@FindBy(name = "username")
	WebElement userN;

	@FindBy(name = "password")
	WebElement passW;

	@FindBy(name = "passwordconfirm")
	WebElement confirmPassW;

	@FindBy(xpath = "//span[contain(text(),'All fields are required.')]")
	WebElement fieldText;

	@FindBy(name = "agreeTerms")
	WebElement agreeTerms;

	@FindBy(xpath = "//button[contains(@type,'submit')]")
	WebElement submitBtn;

	@FindBy(xpath = "//button[contains(@type,'submit')]")
	WebElement submitBtnn;
	
	public SignUpPage() {
		//super();
		PageFactory.initElements(driver, this);
	}

	public boolean verifyCrmLogo() {
		return signUpCrmLogo.isDisplayed();
	}

	public boolean selectEditionDdText() {

		return selectEditionDD_Text.isDisplayed();
	}
	
	
	public void selectEditionDd_Id(String PlanId) {

		select = new Select(editionPaymentPlanId);
		select.selectByVisibleText(PlanId);
		
	}
	public void signUpPageAllFields() {
		firstN.sendKeys(prop.getProperty("firstname"));
		lastN.sendKeys(prop.getProperty("surname"));
		emailId.sendKeys(prop.getProperty("emailId"));
		confirmEmailId.sendKeys(prop.getProperty("emailId"));
		userN.sendKeys(prop.getProperty("username"));
		passW.sendKeys(prop.getProperty("passW"));
		confirmPassW.sendKeys(prop.getProperty("passW"));
		
	}
	
	public boolean verifyFieldText() {
		
		return fieldText.isDisplayed();
	}
	public void verifyInnerText() {
		
//		testUtil.validateInnerText(firstN);
//		testUtil.validateInnerText(lastN);
//		testUtil.validateInnerText(emailId);
//		testUtil.validateInnerText(confirmEmailId);
//		testUtil.validateInnerText(passW);
//		
//		testUtil.validateInnerText(confirmPassW);
	}
	
	
	
}
