package com.crm.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.crm.base.TestBase;

public class TestUtil extends TestBase {

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Himanshu\\git\\FreeCRMTest\\src\\main\\java\\com\\"
			+ "crm\\qa\\testdata\\FreeCrmTestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	public static Select select;

	public void switchToFrame() {
		try {
			driver.switchTo().frame("mainpanel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectDropDown(WebElement dropDownElement, String planId) {

		select = new Select(dropDownElement);
		select.selectByVisibleText(planId);
	}

	// public static Object[][] getTestData(String sheetName) {
	// FileInputStream file = null;
	// try {
	// file = new FileInputStream(TESTDATA_SHEET_PATH);
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// try {
	// book = WorkbookFactory.create(file);
	// } catch (InvalidFormatException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// sheet = book.getSheet(sheetName);
	// Object[][] data = new
	// Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	// // System.out.println(sheet.getLastRowNum() + "--------" +
	// // sheet.getRow(0).getLastCellNum());
	// for (int i = 0; i < sheet.getLastRowNum(); i++) {
	// for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
	// data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
	// // System.out.println(data[i][k]);
	// }
	// }
	// return data;
	// }
	public static Object[][] getTestData(String sheetName) {
		try {
			FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();

			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");

		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
