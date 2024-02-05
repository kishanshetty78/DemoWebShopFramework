package com.tricentis.demowebshop.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeLoggedInPage {
	
	WebDriver driver;

	@FindBy(xpath="//div[@class='header-links']//ul//a[@class='account']")
	private WebElement LoginAccount;
	
	@FindBy(xpath="//a[@class='ico-logout']")
	private WebElement LogOutBtnOption;
	
	public HomeLoggedInPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean LogOutBtnDisplayed()
	{
		boolean DisplayStatus = LogOutBtnOption.isDisplayed();
		return DisplayStatus;
	}
	
	public String getLoggedInAccountdetails(String Account)
	{
		Account = LoginAccount.getText();	
		return Account;
	}
}
