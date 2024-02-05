package com.tricentis.demowebshop.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[@for='FirstName']")
	private WebElement FirstNameMsg;
	
	@FindBy(xpath="//span[@for='LastName']")
	private WebElement LastNameMsg;
	
	@FindBy(xpath="//span[@for='Email']")
	private WebElement EmailMsg;
	
	@FindBy(xpath="//div[@class='validation-summary-errors']/ul/li")
	private WebElement EmailExistMsg;
	
	@FindBy(xpath="//span[@for='Password']")
	private WebElement PaswrdMsg;
	
	@FindBy(xpath="//span[@for='ConfirmPassword']")
	private WebElement CnfrmPswrdMsg;
	
	@FindBy(xpath="//span[@class='required']")
	private WebElement AstreixSym;

	@FindBy(xpath="//input[@name='FirstName']")
	private WebElement FirstNamefield;
	
	@FindBy(xpath="//input[@id='LastName']")
	private WebElement LastNamefield;
	
	@FindBy(xpath="//input[@id='Email']")
	private WebElement Emailfield;
	
	@FindBy(xpath="//input[@id='Password']")
	private WebElement Passwordfield;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	private WebElement CnfrmPasswordfield;
	
	@FindBy(xpath="//input[@name='register-button']")
	private WebElement RegisterBtn;
		
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getAstrexFormat()
	{
		String star = AstreixSym.getText()+" ";
		return star;
	}
	
	public String FirstNameErrMsg()
	{
		String msg = FirstNameMsg.getText();
		return msg;
	}
	
	public String LastNameErrMsg()
	{
		String msg = LastNameMsg.getText();
		return msg;
	}
	
	public String EmailErrMsg()
	{
		String msg = EmailMsg.getText();
		return msg;
	}
	
	public String EmailExistErrMsg()
	{
		String msg = EmailExistMsg.getText();
		return msg;
	}
	
	public String PasswordErrMsg()
	{
		String msg = PaswrdMsg.getText();
		return msg;
	}
	
	public String CnfmPasswordErrMsg()
	{
		String msg = CnfrmPswrdMsg.getText();
		return msg;
	}
	
	public void focusOutAfterCnfrmPwd()
	{
		CnfrmPasswordfield.sendKeys(Keys.TAB);
	}
	
	public void RegisterBthClick_ErrMsgBehaviour()
	{
		RegisterBtn.click();
	}
	
	public RegistrationSuccess registrAllFieldsSuccess(String actualFirstName, String actualLastName, String actualEmailField, String actualPwd, String actualCnfmPwd)
	{
		FirstNamefield.sendKeys(actualFirstName);
		LastNamefield.sendKeys(actualLastName);
		Emailfield.sendKeys(actualEmailField);
		Passwordfield.sendKeys(actualPwd);
		CnfrmPasswordfield.sendKeys(actualCnfmPwd);
		RegisterBtn.click();
		return new RegistrationSuccess(driver);
	}
	
	public void registrWithInvalidMail(String actualFirstName, String actualLastName, String actualEmailField)
	{
		FirstNamefield.sendKeys(actualFirstName);
		LastNamefield.sendKeys(actualLastName);
		Emailfield.sendKeys(actualEmailField);
	}
	
	public void registrValidateMinPwdLength(String actualFirstName, String actualLastName, String actualEmailField, String actualPwd)
	{
		FirstNamefield.sendKeys(actualFirstName);
		LastNamefield.sendKeys(actualLastName);
		Emailfield.sendKeys(actualEmailField);
		Passwordfield.sendKeys(actualPwd);
	}
	
	public void registrPasswordMismatch(String actualFirstName, String actualLastName, String actualEmailField, String actualPwd, String actualCnfmPwd)
	{
		FirstNamefield.sendKeys(actualFirstName);
		LastNamefield.sendKeys(actualLastName);
		Emailfield.sendKeys(actualEmailField);
		Passwordfield.sendKeys(actualPwd);
		CnfrmPasswordfield.sendKeys(actualCnfmPwd);
	}
}
