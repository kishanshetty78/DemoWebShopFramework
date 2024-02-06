package com.tricentis.demowebshop.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@name='Email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@name='Password']")
	private WebElement PasswordField;
	
	@FindBy(xpath="//input[@value='Log in']")
	private WebElement LoginBtn;
	
	@FindBy(xpath="//div[@class='validation-summary-errors']/span")
	private WebElement LoginWarningMsg;
	
	@FindBy(xpath="//div[@class='validation-summary-errors']/ul/li")
	private WebElement CustWarMsg;
	
	@FindBy(xpath="//span[@class='field-validation-error']/span[@for='Email']")
	private WebElement InvalidMailMsg;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String emailText)
	{
		emailField.sendKeys(emailText);
		emailField.sendKeys(Keys.TAB);
	}
		
	public HomeLoggedInPage enterLogincreds(String emailText, String passwordText)
	{
		emailField.sendKeys(emailText);
		PasswordField.sendKeys(passwordText);
		LoginBtn.click();
		return new HomeLoggedInPage(driver);
	}
	
	public String getLoginWarningMsg()
	{
		String msg = LoginWarningMsg.getText();
		return msg;
	}
	
	public String getCustomerWarningMsg()
	{
		String msg = CustWarMsg.getText();
		return msg;
	}
	
	public String invalidEmailMsg()
	{
		String msg = InvalidMailMsg.getText();
		return msg;
	}
	
}
