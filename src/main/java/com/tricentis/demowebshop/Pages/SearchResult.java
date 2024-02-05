package com.tricentis.demowebshop.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResult {
	
	WebDriver driver;
	
	@FindBy(xpath="//strong[@class='result']")
	private WebElement NoSearchProdErrMsg;
	
	@FindBy(partialLinkText="Desktop")
	private WebElement ValidProd;
	
	public SearchResult(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public String getSearchProdErrMsg()
	{
		String msg = NoSearchProdErrMsg.getText();
		return msg;
	}
	
	public boolean getprodResultDisplayStatus()
	{
		boolean result = ValidProd.isDisplayed();
		return result;		
	}
	
	public String getAlertMessage()
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}
}
