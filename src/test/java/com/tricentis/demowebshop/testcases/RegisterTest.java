package com.tricentis.demowebshop.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tricentis.demowebshop.Base.Base;
import com.tricentis.demowebshop.Pages.HomePage;
import com.tricentis.demowebshop.Pages.RegisterPage;
import com.tricentis.demowebshop.Pages.RegistrationSuccess;
import com.tricentis.demowebshop.utils.Utilities;

public class RegisterTest extends Base{

public WebDriver driver;
RegisterPage rgpage;
String astriexsym;

	public RegisterTest()
	{
		super();
	}
	
	@BeforeMethod		
	public void setupregpage()
	{
		driver = setUpDriverandInitializeURL(Prop.getProperty("browserName")); 
		HomePage hpage = new HomePage(driver);
		rgpage = hpage.RegLinkClick();
	}
	
	@Test(priority=2)
	public void ErrorMsgValidation()
	{
		rgpage.RegisterBthClick_ErrMsgBehaviour();
		
		astriexsym = rgpage.getAstrexFormat();
		
		Assert.assertEquals(astriexsym+ rgpage.FirstNameErrMsg(),testProp.getProperty("FirstNameErrMsg"),"Error regarding first name not displayed");
		Assert.assertEquals(astriexsym+ rgpage.LastNameErrMsg(),testProp.getProperty("LastNameErrMsg"),"Error regarding last name not displayed");
		Assert.assertEquals(astriexsym+ rgpage.EmailErrMsg(),testProp.getProperty("EmailErrMsg"),"Error regarding email not displayed");
		Assert.assertEquals(astriexsym+ rgpage.PasswordErrMsg(),testProp.getProperty("PswdErrMsg"),"Error regarding password not displayed");		
		Assert.assertEquals(astriexsym+ rgpage.CnfmPasswordErrMsg(), testProp.getProperty("PswdErrMsg"), "Error regarding confirm password not displayed");
	}
	
	@Test(priority=4)
	public void EmailValidationErrMsg()
	{
		rgpage.registrWithInvalidMail(testProp.getProperty("FirstName"), testProp.getProperty("LastName"), testProp.getProperty("InvalidEmail"));
		
		astriexsym = rgpage.getAstrexFormat();
		Assert.assertEquals(astriexsym+ rgpage.EmailErrMsg(),testProp.getProperty("EmailWrongMsg"),"Email entered is in incorrect format");
	}
	
	@Test(priority=5)
	public void PasswordWithMinChars()
	{
		rgpage.registrValidateMinPwdLength(testProp.getProperty("FirstName"), testProp.getProperty("LastName"), Utilities.GenerateEmailWithTimestamp(), testProp.getProperty("PswdMinchars"));
		rgpage.focusOutAfterCnfrmPwd();		
		
		astriexsym = rgpage.getAstrexFormat();	
		Assert.assertEquals(astriexsym+ rgpage.PasswordErrMsg(),testProp.getProperty("PwdMinChars"),"Password is too short");
	}
	
	@Test(priority=6)
	public void PasswordMismatch()
	{
		rgpage.registrPasswordMismatch(testProp.getProperty("FirstName"), testProp.getProperty("LastName"), Utilities.GenerateEmailWithTimestamp(), testProp.getProperty("validPwd"), testProp.getProperty("CnfvalidPwd"));
		rgpage.focusOutAfterCnfrmPwd();
		
		astriexsym = rgpage.getAstrexFormat();
		Assert.assertEquals(astriexsym+ rgpage.CnfmPasswordErrMsg(),testProp.getProperty("PwdCnfmErrMsg"), "Password and confirm password fields are mismatched");
	}
	
	@Test(priority=1)
	public void SuccessfulRegistr()
	{
		String newEmail = Utilities.GenerateEmailWithTimestamp();
		RegistrationSuccess regSuccess = rgpage.registrAllFieldsSuccess(testProp.getProperty("FirstName"), testProp.getProperty("LastName"), newEmail, testProp.getProperty("validPwd"), testProp.getProperty("validPwd"));
		
		Assert.assertEquals(newEmail,driver.findElement(By.linkText(newEmail)).getText(),"Email ID entered and one being displayed in header are not the same");
		Assert.assertEquals(regSuccess.SuccessMsgHeader(),testProp.getProperty("RegSuccHeader"), "Header displaying sucessful registration not being displayed");
		Assert.assertEquals(regSuccess.SuccessMsgText(),testProp.getProperty("RegSuccMsg"), "Message displaying sucessful registration not being displayed");
		Assert.assertTrue(regSuccess.ContinueBtnPresent(), "Message indicating presence of Continue button not checked");		
	}
	
	@Test(priority=2)
	public void RegistrWithExistEmail()
	{
		rgpage.registrAllFieldsSuccess(testProp.getProperty("FirstName"), testProp.getProperty("LastName"), Prop.getProperty("Email"), testProp.getProperty("validPwd"), testProp.getProperty("validPwd"));
		
		Assert.assertEquals(rgpage.EmailExistErrMsg(), testProp.getProperty("EmailExists"), "Message regarding attempt to reg using existing email id not displayed");
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
}
