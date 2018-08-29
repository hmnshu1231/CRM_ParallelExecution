package com.crm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.crm.Utility.AppConfig;
import com.crm.Utility.WebEventListener;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream fis;
	public static SimpleDateFormat sdf;
	public static Select select;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public static void appLoadPropertiesFile() throws IOException {
		prop = new Properties();
		File file = new File(System.getProperty("user.dir") + "/src/main/java/com/crm/config/Application.properties");
		fis = new FileInputStream(file);
		prop.load(fis);
	}

	public static PhantomJSDriver getGhostDriver() {
		File file = new File(System.getProperty("user.dir") + "/drivers/Phantomjs/phantomjs.exe");
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		driver = new PhantomJSDriver();
		return (PhantomJSDriver) driver;
	}

	public static void getBrowser(String browser) {
		System.out.println(System.getProperty("user.dir"));
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/ChromeDriver/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				options.addArguments("--disable-web-security");
				options.addArguments("--no-proxy-server");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				prefs.put("profile.default_content_setting_values.notifications", 2);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/drivers/IEDriverServer340X32bit.exe");
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				options.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 10000);
				options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
				options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				// options.setCapability(InternetExplorerDriver.NATIVE_EVENTS,true);
				// options.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,UnexpectedAlertBehaviour.ACCEPT);
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				// options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,false);
				options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				// options.setCapability(CapabilityType.PROXY,Proxy.NO_PROXY);

				driver = new InternetExplorerDriver(options);
				driver.manage().window().maximize();
			} else {
				System.err.println("Please Enter Correct Browser");
			}
		} else {
			System.err.println("Please Enter Correct Platform");
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
	}

	public static void loadPropertiesFile() throws IOException {
		prop = new Properties();
		File file = new File(System.getProperty("user.dir") + "/src/main/java/com/crm/config/Locators.properties");
		fis = new FileInputStream(file);
		prop.load(fis);
	}

	public static WebElement getPhantomLocator(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.equalsIgnoreCase("ID") || locatorType.equalsIgnoreCase("Id"))
			return getGhostDriver().findElement(By.id(locatorValue));
		else if (locatorType.equalsIgnoreCase("Class") || locatorType.equalsIgnoreCase("ClassName"))
			return getGhostDriver().findElement(By.className(locatorValue));
		else if (locatorType.equalsIgnoreCase("xpath") || locatorType.equalsIgnoreCase("Xpath"))
			return getGhostDriver().findElement(By.xpath(locatorValue));
		else if (locatorType.equalsIgnoreCase("CSS") || locatorType.equalsIgnoreCase("CssSeclector"))
			return getGhostDriver().findElement(By.cssSelector(locatorValue));
		else if (locatorType.equalsIgnoreCase("LinkText"))
			return getGhostDriver().findElement(By.linkText(locatorValue));
		else if (locatorType.equalsIgnoreCase("TagName"))
			return getGhostDriver().findElement(By.tagName(locatorValue));
		else if (locatorType.equalsIgnoreCase("PartialLinkText"))
			return getGhostDriver().findElement(By.partialLinkText(locatorValue));
		else
			throw new Exception("Unknown Locator type " + locatorType);
	}

	public static WebElement getLocator(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.equalsIgnoreCase("ID") || locatorType.equalsIgnoreCase("Id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.equalsIgnoreCase("Class") || locatorType.equalsIgnoreCase("ClassName"))
			return driver.findElement(By.className(locatorValue));
		else if (locatorType.equalsIgnoreCase("xpath") || locatorType.equalsIgnoreCase("Xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else if (locatorType.equalsIgnoreCase("CSS") || locatorType.equalsIgnoreCase("CssSeclector"))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.equalsIgnoreCase("LinkText"))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.equalsIgnoreCase("TagName"))
			return driver.findElement(By.tagName(locatorValue));
		else if (locatorType.equalsIgnoreCase("PartialLinkText"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else
			throw new Exception("Unknown Locator type " + locatorType);
	}

	public static List<WebElement> getLocators(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.equalsIgnoreCase("ID") || locatorType.equalsIgnoreCase("Id")) {
			return driver.findElements(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("Class") || locatorType.equalsIgnoreCase("ClassName")) {
			return driver.findElements(By.className(locatorValue));
		} else if (locatorType.equalsIgnoreCase("CSS") || locatorType.equalsIgnoreCase("CssSeclector")) {
			return driver.findElements(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("xpath") || locatorType.equalsIgnoreCase("Xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else if (locatorType.equalsIgnoreCase("LinkText"))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.equalsIgnoreCase("TagName"))
			return driver.findElements(By.tagName(locatorValue));
		else if (locatorType.equalsIgnoreCase("PartialLinkText"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else
			throw new Exception("Unknown Locator type " + locatorType);
	}

	public static WebElement getWebElement(String elementName) throws Exception {
		return getLocator(prop.getProperty(elementName));
	}

	public static WebElement getPhantomWebElement(String elementName) throws Exception {
		return getPhantomLocator(prop.getProperty(elementName));
	}

	public static List<WebElement> getWebElements(String elementName) throws Exception {
		return getLocators(prop.getProperty(elementName));
	}

//taking screenshots	
	public String getScreenShot(String imageName) throws IOException {
		if (imageName.equals("")) {
			imageName = "SS";
		}
		String imagelocation = AppConfig.getScreenShotPath();
		sdf = new SimpleDateFormat("dd-MM-YYYY-hh-mm-SS-SSS");
//		String actualImageName = imagelocation+imageName+""+sdf.format(new Date())+".mp4"; //for video file
		String actualImageName = imagelocation + imageName + "" + sdf.format(new Date()) + ".png";

		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(actualImageName);
		FileUtils.copyFile(sourceFile, destFile);
		return actualImageName;
	}
}