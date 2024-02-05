package com.tricentis.demowebshop.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tricentis.demowebshop.Base.Base;
import com.tricentis.demowebshop.Pages.HomeLoggedInPage;
import com.tricentis.demowebshop.Pages.HomePage;
import com.tricentis.demowebshop.Pages.LoginPage;
import com.tricentis.demowebshop.utils.Utilities;

public class LoginTest extends Base{

	public WebDriver driver;
	LoginPage lpage;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod		
	public void setupLginpage()
	{
		driver = setUpDriverandInitializeURL(Prop.getProperty("browserName"));
		HomePage hpage = new HomePage(driver);
		lpage = hpage.LoginLinkClick();
	}
	
	@DataProvider(name="validCredSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data = Utilities.getTestDataFromExcel("Login-creds");
		return data;
	}
	
	@Test(priority=1,dataProvider="validCredSupplier")
	public void verifyLoginwithValidCreds(String email,String Password)
	{
		HomeLoggedInPage hlpage = lpage.enterLogincreds(email, Password);
		
		String account = hlpage.getLoggedInAccountdetails(email);
		
		Assert.assertTrue(hlpage.LogOutBtnDisplayed(),"Log out button not present");
		Assert.assertEquals(email, account,"Both email id's are not identical");		
	
	}
	
	@Test(priority=2)
	public void verifyLoginwithInvalidCreds()
	{
		lpage.enterLogincreds(Utilities.GenerateEmailWithTimestamp(), testProp.getProperty("InvalidPassword"));
				
		String loginwarningmsg = lpage.getLoginWarningMsg();
		String custwarningmsg = lpage.getCustomerWarningMsg();
		
		Assert.assertEquals(loginwarningmsg,testProp.getProperty("LoginErrMsg"),"No error message is being displayed");
		Assert.assertEquals(custwarningmsg,testProp.getProperty("NoCustErrMsg"),"No error message is being displayed");
		
	}
	
	@Test(priority=3)
	public void verifyLoginwithIncorrectEmail()
	{
		lpage.enterLogincreds(Utilities.GenerateEmailWithTimestamp(), Prop.getProperty("Password"));
		
		String loginwarningmsg = lpage.getLoginWarningMsg();
		String custwarningmsg = lpage.getCustomerWarningMsg();
		
		Assert.assertEquals(loginwarningmsg,testProp.getProperty("LoginErrMsg"),"No error message is being displayed");
		Assert.assertEquals(custwarningmsg,testProp.getProperty("NoCustErrMsg"),"No error message is being displayed");				
	}
	
	@Test(priority=4)
	public void verifyLoginwithIncorrectPwd()
	{
		lpage.enterLogincreds(Prop.getProperty("Email"), testProp.getProperty("InvalidPassword"));
		
		String loginwarningmsg = lpage.getLoginWarningMsg();
		String custwarningmsg = lpage.getCustomerWarningMsg();
		
		Assert.assertEquals(loginwarningmsg,testProp.getProperty("LoginErrMsg"),"No error message is being displayed");
		Assert.assertEquals(custwarningmsg,testProp.getProperty("IncorrectPwd"),"No error message is being displayed");
		
	}
	
	@Test(priority=5)
	public void verifyInvalidEmailMsg()
	{
		lpage.enterEmailAddress(testProp.getProperty("InvalidEmail"));
		
		try {
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String emailwarningmsg = lpage.invalidEmailMsg();
		Assert.assertEquals(emailwarningmsg,testProp.getProperty("InvalidMailErrMsg"),"No error message is displayed");
	}
	
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();;
	}
}
