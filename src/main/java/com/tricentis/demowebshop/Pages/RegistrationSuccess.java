package com.tricentis.demowebshop.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationSuccess {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='page-title']/h1")
	private WebElement SuccMsgHeader;
	
	@FindBy(xpath="//div[@class='result']")
	private WebElement SuccMsgText;

	@FindBy(xpath="//input[@value='Continue']")
	private WebElement ContinueBtn;

	public RegistrationSuccess(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String SuccessMsgHeader()
	{
		String msg = SuccMsgHeader.getText();
		return msg;
	}
	
	public String SuccessMsgText()
	{
		String msg = SuccMsgText.getText();
		return msg;
	}
	
	
	public boolean ContinueBtnPresent()
	{
		boolean status = ContinueBtn.isDisplayed();
		return status;
	}
}
