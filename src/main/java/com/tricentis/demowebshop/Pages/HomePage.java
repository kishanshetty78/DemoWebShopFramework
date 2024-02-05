package com.tricentis.demowebshop.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//a[@href='/login']")
	private WebElement LogInLink;
	
	@FindBy(xpath="//a[@href='/register']")
	private WebElement RegLink;
	
	@FindBy(xpath="//input[@id='small-searchterms']")
	private WebElement SearchBox;
	
	@FindBy(xpath="//input[@value='Search']")
	private WebElement SearchButton;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage LoginLinkClick()
	{
		LogInLink.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage RegLinkClick()
	{
		RegLink.click();
		return new RegisterPage(driver);
	}
	
	public SearchResult enterProdSearch(String ProdName)
	{
		SearchBox.sendKeys(ProdName);
		SearchButton.click();
		return new SearchResult(driver);
	}
	
	public SearchResult enterNoProdValue()
	{
		SearchButton.click();
		return new SearchResult(driver);
	}
}
