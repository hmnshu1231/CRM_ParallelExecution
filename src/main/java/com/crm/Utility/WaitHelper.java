package com.crm.Utility;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.base.TestBase;



public class WaitHelper 
{	
	public static void setImplicitWait(long timeout) {
		TestBase.driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
	}
	
	public static void setPageLoadTimeout(long timeout) {
		TestBase.driver.manage().timeouts().pageLoadTimeout(timeout,TimeUnit.SECONDS);
	}
	
	private static WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(TestBase.driver, timeOutInSeconds);
					  wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
					  wait.ignoring(NoSuchElementException.class);
					  wait.ignoring(ElementNotVisibleException.class);
					  wait.ignoring(StaleElementReferenceException.class);
					  wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	public static void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	public static void waitForElement(WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(TestBase.driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}