/*
 * @author Himanshu Malviya
 * 
 */

package com.crm.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.Utility.TestUtil;
import com.crm.base.TestBase;
import com.crm.base.TestEnvironment;
import com.crm.pages.ContactsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

public class ContactsPageTest extends TestEnvironment {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName = "contacts";

	@BeforeClass(dependsOnMethods={"setUp"})
	public void initialization() throws IOException {

		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(TestBase.prop.getProperty("username"), TestBase.prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}

	@Test(priority = 1, enabled = true)
	public void verifyContactsPageLabel() {
		//contactsPage = homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}

	@Test(priority = 3, enabled = true)
	public void selectMultipleContactsTest() {
		//contactsPage = homePage.clickOnContactsLink();
		contactsPage.selectContactsByName("David Cris");
		contactsPage.selectContactsByName("Mukta Sharma");

	}

	@Test(priority = 4, enabled = true)
	public void selectSingleContactsTest() {
	contactsPage = homePage.clickOnContactsLink();
		contactsPage.selectContactsByName("Mukta Sharma");
	}

	@DataProvider
	public Object[][] CRMTestData() throws FileNotFoundException {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	@Test(priority = 2, dataProvider = "CRMTestData", enabled = true)
	public void validateCreateNewContact(String title, String firstName, String lastName, String company)
			throws InterruptedException {

		contactsPage = homePage.clickOnNewContactLink();

		contactsPage.createNewContact(title, firstName, lastName, company);
		contactsPage = homePage.clickOnContactsLink();

	}

	@Test(priority = 5, dataProvider = "CRMTestData", enabled = true)
	public void deleteContactsFromList(String title, String firstName, String lastName, String company)
			throws InterruptedException {
		
		contactsPage.selectContactsByData(firstName);

	}

	@Test(dependsOnMethods = { "deleteContactsFromList" })
	public void deleteSelectedContacts() throws InterruptedException {
		
		contactsPage.deleteContactsNew();
	}
}