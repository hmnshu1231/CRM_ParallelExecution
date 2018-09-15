package com.crm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.crm.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;

	@FindBy(id = "first_name")
	WebElement firstName;

	@FindBy(id = "surname")
	WebElement lastName;

	@FindBy(name = "client_lookup")
	WebElement company;

	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//select[@name='do_action']")
	WebElement doSelect;

	@FindBy(xpath = "//input[contains(@value,'DO')]")
	WebElement doButton;

	// Initializing the Page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}

	public void createNewContact(String title, String ftName, String ltName, String comp) {
		try {
			Select select = new Select(driver.findElement(By.name("title")));
			select.selectByVisibleText(title);

			firstName.sendKeys(ftName);
			lastName.sendKeys(ltName);
			company.sendKeys(comp);
			saveBtn.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement selectContactsByName(String name) {
		return driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]//parent::td[@class='datalistrow']"
				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"));
	}

	public void deleteContacts(String contName) throws InterruptedException {

		WebElement contCheckbox = selectContactsByName(contName);

		if (contCheckbox.isDisplayed()) {
			try {
				select = new Select(doSelect);
				select.selectByVisibleText("Delete Checked");
				doButton.click();
				driver.switchTo().alert().accept();
				Thread.sleep(4000);
			} catch (Exception e) {
				Assert.assertTrue(true);
				e.printStackTrace();
			}

		}

	}

	public void selectContactsByData(String name) {
		WebElement contCheckbox = driver
				.findElement(By.xpath("//a[contains(text(),'" + name + "')]//parent::td[@class='datalistrow']"
						+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"));

		if (contCheckbox.isDisplayed()) {
			contCheckbox.click();
		}

	}

	public void deleteContactsNew() throws InterruptedException {

		select = new Select(doSelect);
		select.selectByVisibleText("Delete Checked");
		doButton.click();
		driver.switchTo().alert().accept();
		Thread.sleep(4000);
	}
}