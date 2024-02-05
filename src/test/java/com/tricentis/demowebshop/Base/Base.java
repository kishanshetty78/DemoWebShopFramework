package com.tricentis.demowebshop.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.tricentis.demowebshop.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties Prop,testProp;
	
	public Base()
	{
		Prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tricentis\\demowebshop\\config\\Config.Properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			Prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		testProp = new Properties();
		File tsfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tricentis\\demowebshop\\Testdata\\Testdata.properties");
		
		try {
			FileInputStream td = new FileInputStream(tsfile);
			testProp.load(td);
		}
		catch(IOException n)
		{	
			n.printStackTrace();
		}
	}

	public WebDriver setUpDriverandInitializeURL(String BrowserName)
	{
		if(BrowserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-extensions");
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
		}
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions fp = new FirefoxOptions();
			fp.addPreference("app.update.auto", false);
			fp.addPreference("app.update.enabled", false);
			driver = new FirefoxDriver(fp);
		}
		else if(BrowserName.equalsIgnoreCase("edge") || BrowserName.equalsIgnoreCase("microsoftedge") || BrowserName.equalsIgnoreCase("microsoft edge"))
		{
			driver = new EdgeDriver();
		}
		else if(BrowserName.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(Prop.getProperty("url"));
		
		return driver;
	}
}
