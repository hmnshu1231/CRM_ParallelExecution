package com.crm.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig 
{
	static File appFile = new File("C:\\Users\\Himanshu\\eclipse-workspace\\ExtentReportForCRM\\src\\main\\java\\com\\crm\\config\\Application.properties");
	static FileInputStream fis;
	static Properties prop = new Properties();
	
	public static String getURL() throws IOException
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("URL");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}
	
	public static String getBrowser() throws IOException
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("Browser");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}
	
	public static String getExcelPath() throws IOException
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("DataSheetLocation");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}
	
	public static String getReportPath() throws IOException
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("ExtentReportLocation");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}
	
	public static String getScreenShotPath() throws IOException
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("ScreenshotLocation");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}

	public static String getExtent_ConfigXML() throws IOException
{
	try
	{
		fis = new FileInputStream(appFile);
		prop = new Properties();
		prop.load(fis);
		
		return prop.getProperty("ExtentConfigXML");
	}
	catch(Exception e){ e.printStackTrace();}
	return null;
}

	public static String getMacyURL() throws IOException
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("MacyURL");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}
	
	public static String getMacyData() throws IOException
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("MacyProfileSheet");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}
	

}
